package com.miu.se.common.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetShopDTO extends PostShopDTO {
    private Long Id;
    private GetUserDTO user;
}
