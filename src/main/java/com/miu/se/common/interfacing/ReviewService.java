package com.miu.se.common.interfacing;

import com.miu.se.common.entity.Review;

import java.util.List;

public interface ReviewService {
    public Review save(Review review);

    public void deleteById(Long id);

    public List<Review> getAll();
}
