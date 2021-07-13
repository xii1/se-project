package com.miu.se.common.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetReviewDTO extends PostReviewDTO {
    Long id;
    private int rate;
}
