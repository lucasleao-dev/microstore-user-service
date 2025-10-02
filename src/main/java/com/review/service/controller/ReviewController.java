package com.review.service.controller;

import com.review.service.model.Review;
import com.review.service.service.ReviewService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reviews")
public class ReviewController {

    private final ReviewService service;

    public ReviewController(ReviewService service) {
        this.service = service;
    }

    @PostMapping
    public Review createReview(@RequestBody Review review) {
        return service.saveReview(review);
    }

    @GetMapping("/{productId}")
    public List<Review> getReviewsByProduct(@PathVariable String productId) {
        return service.getReviewsByProduct(productId);
    }

    @GetMapping
    public List<Review> getAllReviews() {
        return service.getAllReviews();
    }
}
