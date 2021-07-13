package com.miu.se.common.response;

import lombok.Getter;
import lombok.Setter;

/**
 * @author duong
 */
@Getter
@Setter
public class GetTopSoldCategoryResponse {
    private String categoryName;
    private Long numOfSold;

    public GetTopSoldCategoryResponse() {
    }

    public GetTopSoldCategoryResponse(String categoryName, Long numOfSold) {
        this.categoryName = categoryName;
        this.numOfSold = numOfSold;
    }
}
