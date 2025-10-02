package com.review.service.service;

import com.review.service.model.Review;
import com.review.service.repository.ReviewRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {

    private final ReviewRepository repository;

    public ReviewService(ReviewRepository repository) {
        this.repository = repository;
    }

    public Review saveReview(Review review) {
        return repository.save(review);
    }

    public List<Review> getReviewsByProduct(String productId) {
        return repository.findByProductId(productId);
    }

    public List<Review> getAllReviews() {
        return repository.findAll();
    }
}
