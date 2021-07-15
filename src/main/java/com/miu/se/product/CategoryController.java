package com.miu.se.product;

import com.miu.se.common.dto.GetCategoryDTO;
import com.miu.se.common.dto.GetProductDTO;
import com.miu.se.common.dto.PostCategoryDTO;
import com.miu.se.common.dto.PostProductDTO;
import com.miu.se.common.entity.Category;
import com.miu.se.common.entity.Order;
import com.miu.se.common.entity.OrderItem;
import com.miu.se.common.entity.Product;
import com.miu.se.common.interfacing.CategoryService;
import com.miu.se.common.interfacing.OrderService;
import com.miu.se.common.request.GetTopSoldCategoriesRequest;
import com.miu.se.common.response.GetTopSoldCategoryResponse;
import com.miu.se.common.util.TriFunction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.InvalidParameterException;
import java.util.Calendar;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.function.BiPredicate;
import java.util.stream.Collectors;

@RestController
@RequestMapping("categories")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @Autowired
    private OrderService orderService;

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public GetCategoryDTO save(@RequestBody PostCategoryDTO category) {
        return categoryService.save(category.toCategory()).toGetCategoryDTO();
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<GetCategoryDTO> getAll(){
        List<Category> categories = categoryService.getAll();
        return categories.stream().map(Category::toGetCategoryDTO).collect(Collectors.toList());
    }

//    @DeleteMapping
//    public void delete(@PathVariable Long id) {
//        categoryService.deleteById(id);
//    }

//    @PostMapping(path = "/{categoryId}/products", consumes = MediaType.APPLICATION_JSON_VALUE)
//    public GetProductDTO addProduct(@PathVariable Long categoryId, @RequestBody PostProductDTO product) {
//        return categoryService.addProduct(categoryId, product.toProduct()).toGetProductDTO();
//    }

    @GetMapping(path = "/{categoryId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<GetProductDTO> getProducts(@PathVariable Long categoryId) {
        List<Product> products = categoryService.getProducts(categoryId);
        return products.stream().map(Product::toGetProductDTO).collect(Collectors.toList());
    }

    /**
     * Set the most sold categories of the year.
     * Count the number of order in category + year.
     * @param request input parameter
     * @return list of category
     */
//    @GetMapping(path = "topSoldCategories")
//    public List<GetTopSoldCategoryResponse> getTopSoldCategories(GetTopSoldCategoriesRequest request) {
//        // Validate data.
//        if (request == null || !request.isValid()) {
//            throw new NoSuchElementException();
//        }
//        Integer limit = request.getLimit();
//        Integer year = request.getYear();
//        if (limit < 1 || year < 1) {
//            throw new InvalidParameterException();
//        }
//
//        // Prepare data source.
//        List<Category> categories = categoryService.getAll();
//        List<Order> orders = orderService.getAll();
//
//        // Filter order in a year.
//        BiPredicate<Order, Integer> filterOrderInThisYear = (o, iYear) -> {
//            if (o.getCreatedAt() == null) {
//                return false;
//            }
//            Calendar calendar = Calendar.getInstance();
//            calendar.setTime(o.getCreatedAt());
//            return calendar.get(Calendar.YEAR) == iYear;
//        };
//
//        // Filter order items in a category
//        BiPredicate<OrderItem, Category> filterOrderItemsById = (oi, iCategory) -> {
//            if (oi.getProduct() == null ||
//                    oi.getProduct().getCategories() == null) {
//                return false;
//            }
//            return oi
//                    .getProduct()
//                    .getCategoryId() == iCategory.getId();
//        };
//
//        // Count the number of order in this category
//        TriFunction<List<Order>, Category, Integer, Long> getNumberOfCategoryOrderInAYear = (iOrders, iCategory, iYear) ->
//                (long) iOrders
//                        .stream()
//                        .filter(o -> filterOrderInThisYear.test(o, iYear))
//                        .flatMap(o -> o.getItems().stream())
//                        .filter(i -> filterOrderItemsById.test(i, iCategory))
//                        .collect(Collectors.groupingBy(oi -> oi.getOrder()))
//                        .entrySet()
//                        .size();
//
//        return categories
//                .stream()
//                .map(c -> {
//                    Long numOfOrder = getNumberOfCategoryOrderInAYear.apply(orders, c, year);
//                    Pair<Category, Long> pair = Pair.of(c, numOfOrder);
//                    System.out.println("Pair: " + numOfOrder + ". Category: " + c.getName());
//                    return pair;
//                })
//                .filter(p -> p.getSecond() > 0)
//                .sorted((p1, p2) -> (int) (p2.getSecond() - p1.getSecond()))
//                .limit(limit)
//                .map(r -> new GetTopSoldCategoryResponse(r.getFirst().getName(), r.getSecond()))
//                .collect(Collectors.toList());
//    }
}
