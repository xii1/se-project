package com.miu.se.product;

import com.miu.se.common.dto.GetProductDTO;
import com.miu.se.common.dto.PostReviewDTO;
import com.miu.se.common.entity.Order;
import com.miu.se.common.entity.Product;
import com.miu.se.common.entity.Review;
import com.miu.se.common.entity.User;
import com.miu.se.common.interfacing.OrderService;
import com.miu.se.common.interfacing.ProductService;
import com.miu.se.common.interfacing.ReviewService;
import com.miu.se.common.request.GetTopRatingProductsRequest;
import com.miu.se.common.request.UsersHavingLowestRatingGivenProductIdRequest;
import com.miu.se.common.response.GetTopRatingProductResponse;
import com.miu.se.common.util.TriFunction;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.InvalidParameterException;
import java.util.Calendar;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

@RestController
@RequestMapping("products")
public class ProductController {
    @Autowired
    ProductService productService;

    @Autowired
    OrderService orderService;

    @Autowired
    ReviewService reviewService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<GetProductDTO> getAll() {
        List<Product> products = productService.getAll();
        return products.stream().map(Product::toGetProductDTO).collect(Collectors.toList());
    }

//    @Operation(security = @SecurityRequirement(name = "auth"))
//    @DeleteMapping("/{id}")
//    public void delete(@PathVariable Long id) {
//        productService.deleteById(id);
//    }

//    @PostMapping(value = "/{id}/reviews", consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
//    public Review addReview(@PathVariable Long id, @RequestBody PostReviewDTO review) {
//        return productService.addReview(id, review.toReview());
//    }

    /**
     * Get n products user rating the most in a year.
     * @param request
     * @return
     */
    @GetMapping("topRatingProducts")
    public List<GetTopRatingProductResponse> getTopRatingProducts(GetTopRatingProductsRequest request) {
        if (request == null || !request.isValid()) {
            throw new NoSuchElementException();
        }
        Integer limit = request.getLimit();
        Integer year = request.getYear();
        if (limit < 1 || year < 1) {
            throw new InvalidParameterException();
        }
        List<Product> products = productService.getAll();
        List<Order> orders = orderService.getAll();
        List<Review> reviews = reviewService.getAll();
        BiFunction<Product, Integer, Double> getProductRating = (iProduct, iYear) ->
                reviews
                .stream()
                .filter(r -> {
                    if (r.getCreatedAt() == null) {
                        return false;
                    }
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTime(r.getCreatedAt());
                    return calendar.get(Calendar.YEAR) == iYear;
                })
                .filter(r -> r.getSpecialProductId() != null && r.getSpecialProductId().equals(iProduct.getId()))
                .mapToDouble(r -> r.getRate())
                .average().orElse(0);

        TriFunction<List<Order>, Product, Integer, Long> getNumberOfProductOrderInAYear = (iOrders, iProduct, iYear) ->
                iOrders
                .stream()
                        .filter(o -> {
                            if (o.getCreatedAt() == null) {
                                return false;
                            }
                            Calendar calendar = Calendar.getInstance();
                            calendar.setTime(o.getCreatedAt());
                            return calendar.get(Calendar.YEAR) == iYear;
                        })
                .flatMap(o -> o.getItems().stream())
                .filter(i -> i.getProduct() != null && i.getProduct().getId() == iProduct.getId())
                .count();

        return products
                .stream()
                .filter(p -> getNumberOfProductOrderInAYear.apply(orders, p, year) > 0)
                .map(p -> {
                    double rating = getProductRating.apply(p, year);
                    Pair<Product, Double> pair = Pair.of(p, rating);
                    return pair;
                })
                .filter(p -> p.getSecond() > 0)
                .sorted((p1, p2) -> (int) (p2.getSecond() - p1.getSecond()))
                .limit(limit)
                .map(r -> new GetTopRatingProductResponse(r.getFirst().getName(), r.getSecond()))
                .collect(Collectors.toList());
    }


    @Operation(security = @SecurityRequirement(name = "auth"))
    @GetMapping(path = "/getProductsHavingTheWorstReview")
    public List<String> getProductsHavingTheWorstReview(@RequestParam Integer year, @RequestParam Integer limit) {
        //Get Data
        List<Product> products = productService.getAll();

        //limit K worst products in the certain YEAR
        TriFunction<List<Product>, Integer ,Integer, List<String>> worstProductsGivenYear = (prods, y, k) ->
                prods.stream()
                    .flatMap(p->p.getReviews().stream())
                    .filter(r->r.getCreatedAt().getYear() == y)
                    .collect(Collectors.groupingBy(r->r.getProduct().getName(), Collectors.summingInt(r->r.getRate())))
                    .entrySet().stream()
                    .sorted(Comparator.comparing(kv->kv.getValue()))
                    .limit(k)
                    .map(kv->kv.getKey())
                    .collect(Collectors.toList());
        return worstProductsGivenYear.apply(products, year, limit);
    }

    @Operation(security = @SecurityRequirement(name = "auth"))
    @GetMapping(path = "/getUsersHavingLowestRatingGivenProductId")
    public List<User> getUsersHavingLowestRatingGivenProductId(UsersHavingLowestRatingGivenProductIdRequest request) {
        final Integer LOWEST_RATING = 1;
        //Get Data
        List<Product> products = productService.getAll();


        //Top users having lowest Rating (1*) with a given product id
        TriFunction<List<Product>, Long, Integer, List<User>> getUsersHavingLowestRatingGivenProductId = (listProduct, prodId, k) ->
                listProduct.stream()
                    .filter(p->p.getId() == prodId)
                    .flatMap(p->p.getReviews().stream())
                    .filter(r->r.getRate() == LOWEST_RATING)
                    .collect(Collectors.groupingBy(Review::getUser))
                    .entrySet().stream()
                    .limit(k)
                    .map(Map.Entry::getKey)
                    .collect(Collectors.toList());

        List<User> kkk = getUsersHavingLowestRatingGivenProductId.apply(products, request.getProductId(), request.getK());
        System.out.println("===");
        return getUsersHavingLowestRatingGivenProductId.apply(products, request.getProductId(), request.getK());
    }

}
