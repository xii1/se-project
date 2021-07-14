package com.miu.se.common.response;

import lombok.Getter;
import lombok.Setter;

/**
 * @author duong
 */
@Getter
@Setter
public class GetTopVipUserResponse {
    private Long userId;
    private Double totalBuyAmount;

    public GetTopVipUserResponse() {

    }

    public GetTopVipUserResponse(Long userId, Double totalBuyAmount) {
        this.userId = userId;
        this.totalBuyAmount = totalBuyAmount;
    }
}
