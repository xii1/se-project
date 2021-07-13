package com.miu.se.common.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.miu.se.common.dto.GetProductDTO;
import com.miu.se.common.dto.PostProductDTO;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Getter
@Setter
@Table(name = "product")
public class Product {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private int availableQuantity;

    @Column
    private double unitPrice;

    @Column
    private Long categoryId;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private List<ProductResource> resources;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "product")
    private List<Review> reviews;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "created_by")
    private User createdBy;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "products")
    private List<Category> categories;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "product")
    private List<OrderItem> orderItems;

    public PostProductDTO toProductDTO() {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(this, PostProductDTO.class);
    }

    public GetProductDTO toGetProductDTO() {
        ModelMapper modelMapper = new ModelMapper();
        GetProductDTO getProductDTO = modelMapper.map(this, GetProductDTO.class);
        if(this.getReviews() != null) {
            getProductDTO.setReviews(getReviews().stream().map(Review::toGetReviewDTO).collect(Collectors.toList()));
        }
        return getProductDTO;
    }
}
