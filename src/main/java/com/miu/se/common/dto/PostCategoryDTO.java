package com.miu.se.common.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.miu.se.common.entity.Category;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostCategoryDTO {
    String name;

    @JsonIgnore
    public Category toCategory() {
        Category category = new Category();
        category.setName(name);
        return category;
    }
}
