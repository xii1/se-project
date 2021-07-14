package com.miu.se.common.request;

public class UsersHavingLowestRatingGivenProductIdRequest {
    private Long productId;
    private Integer k;

    public UsersHavingLowestRatingGivenProductIdRequest(Long productId, Integer k) {
        this.productId = productId;
        this.k = k;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Integer getK() {
        return k;
    }

    public void setK(Integer k) {
        this.k = k;
    }
}
