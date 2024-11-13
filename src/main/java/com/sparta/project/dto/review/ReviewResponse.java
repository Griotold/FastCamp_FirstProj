package com.sparta.project.dto.review;

import com.sparta.project.domain.Review;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public record ReviewResponse (
        String reviewId,
        String orderId,
        Long user_id,
        String storeId,
        String nickname,
        String content,
        Integer score,
        String created_at,
        String updated_at
) {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;

    public static ReviewResponse from(Review review) {
        return new ReviewResponse(
                review.getReviewId(),
                review.getOrder().getOrderId(),
                review.getUser().getUserId(),
                review.getStore().getStoreId(),
                review.getUser().getNickname(),
                review.getContent(),
                review.getScore(),
                review.getCreatedAt().format(formatter),
                review.getUpdatedAt().format(formatter)
        );
    }
}
