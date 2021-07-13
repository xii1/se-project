package com.miu.se.common.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.miu.se.common.dto.GetReviewDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.modelmapper.ModelMapper;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "review")
@NoArgsConstructor
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private int rate;

    @Column
    private String comment;

    @Column
    private Long specialProductId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

    @Column
    private Date createdAt;

    @Column
    private Date updatedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    Product product;

    public GetReviewDTO toGetReviewDTO() {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(this, GetReviewDTO.class);
    }

}
