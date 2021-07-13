package com.miu.se.common.request;

public class ProductsHavingWorstReviewRequest {
    private int year;
    private int limit;

    public ProductsHavingWorstReviewRequest(int year, int limit) {
        this.year = year;
        this.limit = limit;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }
}
