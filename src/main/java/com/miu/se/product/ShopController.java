package com.miu.se.product;

import com.miu.se.common.dto.GetCategoryDTO;
import com.miu.se.common.dto.GetProductDTO;
import com.miu.se.common.dto.GetShopDTO;
import com.miu.se.common.dto.PostProductDTO;
import com.miu.se.common.dto.PostShopDTO;
import com.miu.se.common.entity.Category;
import com.miu.se.common.entity.Product;
import com.miu.se.common.entity.Shop;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("shops")
public class ShopController {

    @Autowired
    ShopService shopService;

    @Autowired
    DefaultShopService defaultShopService;

    @Operation(security = @SecurityRequirement(name = "auth"))
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public GetShopDTO save(@RequestBody PostShopDTO shop) {
        Shop shop1 = shopService.save(shop.toShop());
        return shop1.toGetShopDTO();
    }

    @Operation(security = @SecurityRequirement(name = "auth"))
    @PostMapping(path = "{shopId}/categories/{categoryId}/products", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public GetProductDTO createProduct(@PathVariable Long shopId, @PathVariable Long categoryId, @RequestBody PostProductDTO postProductDTO) {
        Product p = shopService.save(postProductDTO.toProduct(), shopId, categoryId);
        return p.toGetProductDTO();
    }

    @Operation(security = @SecurityRequirement(name = "auth"))
    @GetMapping(path = "{id}/profit")
    public Double getProfit(@PathVariable Long id, @RequestParam Integer month, @RequestParam Integer year) {
        Shop shop = shopService.getOne(id);
        return defaultShopService.getProfit(shop, month, year);
    }

    @Operation(security = @SecurityRequirement(name = "auth"))
    @GetMapping(path = "{id}/products")
    public List<GetProductDTO> getTopProducts(@PathVariable Long id, @RequestParam Integer month, @RequestParam Integer year, @RequestParam Integer top) {
        Shop shop = shopService.getOne(id);
        List<Product> products = defaultShopService.getTopProducts(shop, month, year, top);
        return products.stream().map(Product::toGetProductDTO).collect(Collectors.toList());
    }

    @Operation(security = @SecurityRequirement(name = "auth"))
    @GetMapping(path = "{id}/categories")
    public List<GetCategoryDTO> getTopProductsPerCategory(@PathVariable Long id, @RequestParam Integer month, @RequestParam Integer year, @RequestParam Integer top) {
        Shop shop = shopService.getOne(id);
        List<Category> categories = defaultShopService.getTopNProductPerCategoryOfYear(shop, month, year, top);
        return categories.stream().map(Category::toGetCategoryDTO).collect(Collectors.toList());
    }
}
