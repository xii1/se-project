package com.miu.se.common.dto;

import com.miu.se.common.entity.Product;
import com.miu.se.common.entity.ProductResource;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;

import java.util.List;

@Getter
@Setter
public class PostProductDTO {
    private String name;
    private int availableQuantity;
    private double unitPrice;
    private List<ProductResource> resources;

    public Product toProduct() {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(this, Product.class);
    }
}
