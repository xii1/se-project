package com.miu.se.common.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class GetProductDTO extends PostProductDTO {
    Long id;
    List<GetReviewDTO> reviews;
}
