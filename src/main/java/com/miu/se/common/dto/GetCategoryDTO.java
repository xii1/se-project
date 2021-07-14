package com.miu.se.common.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.miu.se.common.entity.Category;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;

import java.util.List;

@Getter
@Setter
public class GetCategoryDTO extends PostCategoryDTO {
    Long id;
    List<GetProductDTO> products;

    @JsonIgnore
    @Override
    public Category toCategory() {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(this, Category.class);
    }
}
