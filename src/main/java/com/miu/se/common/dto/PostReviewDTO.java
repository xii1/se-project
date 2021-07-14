package com.miu.se.common.dto;

import com.miu.se.common.entity.Review;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostReviewDTO {
    String comment;

    public Review toReview() {
        Review review = new Review();
        review.setComment(comment);
        return review;
    }
}
