package com.miu.se.common.response;

import lombok.Getter;
import lombok.Setter;

/**
 * @author duong
 */
@Getter
@Setter
public class GetTopRatingProductResponse {
    private String productName;
    private Double rating;

    public GetTopRatingProductResponse() {
    }

    public GetTopRatingProductResponse(String productName, Double rating) {
        this.productName = productName;
        this.rating = rating;
    }
}
