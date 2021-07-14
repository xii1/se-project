package com.miu.se.common.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

/**
 * @author duong
 */
@Getter
@Setter
public class GetTopSoldCategoriesRequest {
    private Integer year;
    private Integer limit;

    @JsonIgnore
    public boolean isValid() {
        return this.year != null && this.limit != null;
    }
}
