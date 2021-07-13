package com.miu.se.user;

import com.miu.se.common.dto.ChangePasswordDTO;
import com.miu.se.common.dto.PostUserDTO;
import com.miu.se.common.entity.Order;
import com.miu.se.common.entity.Product;
import com.miu.se.common.entity.Role;
import com.miu.se.common.entity.ShippingAddress;
import com.miu.se.common.entity.User;
import com.miu.se.common.interfacing.OrderService;
import com.miu.se.common.interfacing.UserService;
import com.miu.se.common.request.GetTopVipUserRequest;
import com.miu.se.common.response.GetTopVipUserResponse;
import com.miu.se.common.util.PasswordUtil;
import com.miu.se.common.util.TriFunction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.security.InvalidParameterException;
import java.security.NoSuchAlgorithmException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.stream.Collectors;

/**
 * @author XIII
 */
@RestController
@RequestMapping("users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private OrderService orderService;

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(Exception.class)
    public Map<String, String> handleExceptions(
            Exception ex) {
        Map<String, String> errors = new HashMap<>();
        errors.put("message", ex.getMessage());
        return errors;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<User> getAll(){
        return userService.getAll();
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public User getUser(@PathVariable("id") Long id) {
        return userService.getUserById(id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public User addUser(@RequestBody PostUserDTO user) throws NoSuchAlgorithmException {
        user.setPassword(PasswordUtil.encode(user.getPassword()));
    //content type application json
        return userService.save(user.toUser());
    }

    @PostMapping("/{userId}/roles/{role}")
    public void addRolesForUser(@PathVariable Long userId, @PathVariable Role role) {
        userService.addRoles(userId, role);
    }

    @PatchMapping("/changePWD")
    public void changePWD(@Valid @RequestBody ChangePasswordDTO changePasswordDTO) throws Exception {
        User user = userService.getUserById(0L);
        if(PasswordUtil.encode(changePasswordDTO.getOldPassword()).toUpperCase().equals(user.getPassword().toUpperCase())) {
            user.setPassword(PasswordUtil.encode(changePasswordDTO.getNewPassword()));
            userService.save(user);
        } else {
            throw new Exception("old password not match");
        }
    }

    @PatchMapping("/editProfile/{userId}")
    public void editProfile(@PathVariable Long userId, @RequestBody User u) throws NoSuchAlgorithmException {
        User user = userService.getUserById(userId);
        user.setNickname(u.getNickname());
        user.setBio(u.getBio());
        user.setGender(u.getGender());
        user.setDob(u.getDob());
        user.setPhoneNumber(u.getPhoneNumber());
        userService.save(user);
    }

    @PatchMapping("/{userId}/editShippingAddr/{shippingAddrId}")
    public void editShippingAddr(@PathVariable Long userId, @PathVariable Long shippingAddrId, @RequestBody ShippingAddress shippingAddress) {
        userService.editShippingAddr(userId, shippingAddrId, shippingAddress);
    }

    @GetMapping(value = "/getShippingAddrById/{shippingAddrId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ShippingAddress getShippingAddrById(@PathVariable("shippingAddrId") Long id) {
        return userService.getShippingAddrById(id);
    }

    @RequestMapping(value = "/exists/{email}", method = RequestMethod.HEAD)
    public ResponseEntity exists(@PathVariable String email) {
        final boolean isExists = userService.checkExists(email);
        return new ResponseEntity<>(isExists ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @PostMapping(value = "/register", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> register(@Valid @RequestBody PostUserDTO user) throws NoSuchAlgorithmException {
        final boolean isExists = userService.checkExists(user.getEmail());
        if (isExists) {
            return new ResponseEntity<>("Email is exists", HttpStatus.OK);
        } else {
            user.setPassword(PasswordUtil.encode(user.getPassword()));
            userService.save(user.toUser());
            return new ResponseEntity<>("Done", HttpStatus.CREATED);
        }
    }

    @GetMapping(path = "vipUsers")
    public List<GetTopVipUserResponse> getVipUsers(GetTopVipUserRequest request) {
        if (request == null || !request.isValid()) {
            throw new NoSuchElementException();
        }
        Integer limit = request.getLimit();
        Integer year = request.getYear();
        if (limit < 1 || year < 1) {
            throw new InvalidParameterException();
        }
        List<Order> orders = orderService.getAll();
        List<User> users = userService.getAll();

        // Filter order in a year.
        BiPredicate<Order, Integer> filterOrderInThisYear = (o, iYear) -> {
            if (o.getCreatedAt() == null) {
                return false;
            }
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(o.getCreatedAt());
            return calendar.get(Calendar.YEAR) == iYear;
        };

        // Count the number of order in this category
        TriFunction<List<Order>, User, Integer, Double> getSumOrderAmountOfAUser = (iOrders, iUser, iYear) ->
                 iOrders
                        .stream()
                        .filter(o -> filterOrderInThisYear.test(o, iYear))
                        .filter(o -> o.getSpecialUserId() != null && o.getSpecialUserId().equals(iUser.getId()))
                        .flatMap(o -> o.getItems().stream())
                        .mapToDouble(oi -> oi.calculateCost())
                        .sum();

        return users
                .stream()
                .map(u -> {
                    Double sumAmount = getSumOrderAmountOfAUser.apply(orders, u, year);
                    Pair<User, Double> pair = Pair.of(u, sumAmount);
                    System.out.println("Pair: " + sumAmount + ". User: " + u.getId());
                    return pair;
                })
                .filter(p -> p.getSecond() > 0)
                .sorted((p1, p2) -> (int) (p2.getSecond() - p1.getSecond()))
                .limit(limit)
                .map(r -> new GetTopVipUserResponse(r.getFirst().getId(), r.getSecond()))
                .collect(Collectors.toList());
    }

    @GetMapping(path = "/getTheWorstProductsHavingLowestRatingGivenCategoryId")
    public List<Product> getTheWorstProductsHavingLowestRatingGivenCategoryId(Long categoryId) {
        final Integer LOWEST_RATING = 1;
        //Get Data
        List<User> users = userService.getAll();

        BiFunction<List<User>, Long, List<Product>> getTheWorstProductsHavingLowestRatingGivenCategoryId = (listUsers, cId) ->
                listUsers.stream()
                    .flatMap(u->u.getReviews().stream())
                    .filter(r->r.getRate() == LOWEST_RATING)
                    .map(r->r.getProduct()).filter(p->p.getCategories().stream().anyMatch(category -> category.getId() == cId))
                    .collect(Collectors.groupingBy(p->p))
                    .entrySet().stream()
                    .map(kv->kv.getKey())
                    .collect(Collectors.toList());

        return getTheWorstProductsHavingLowestRatingGivenCategoryId.apply(users, categoryId);
    }
}
