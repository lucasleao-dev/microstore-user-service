package com.review.service.service.controller;

import com.review.service.model.Review;
import com.review.service.repository.ReviewRepository;
import com.review.service.service.ReviewService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ReviewControllerValidationTest {

    private ReviewRepository repository;
    private ReviewService service;

    @BeforeEach
    void setup() {
        repository = mock(ReviewRepository.class);
        service = new ReviewService(repository);
    }

    @Test
    void testCreateReviewValidData() {
        Review review = new Review("produto123", "usuario123", "Ótimo produto!", 5);
        Review savedReview = new Review(1L, "produto123", "usuario123", "Ótimo produto!", 5, LocalDateTime.now());

        when(repository.save(review)).thenReturn(savedReview);

        Review created = service.saveReview(review);

        assertNotNull(created.getId());
        assertEquals(5, created.getRating());
        assertEquals("Ótimo produto!", created.getComment());
        verify(repository, times(1)).save(review);
    }

    @Test
    void testCreateReviewInvalidRating() {
        Review review = new Review("produto123", "usuario123", "Bom produto", 6); // inválido

        Exception exception = assertThrows(RuntimeException.class, () -> {
            if (review.getRating() < 1 || review.getRating() > 5) {
                throw new RuntimeException("Rating inválido: deve ser entre 1 e 5");
            }
            service.saveReview(review);
        });

        assertEquals("Rating inválido: deve ser entre 1 e 5", exception.getMessage());
    }

    @Test
    void testCreateReviewEmptyComment() {
        Review review = new Review("produto123", "usuario123", "", 4); // comentário vazio

        Exception exception = assertThrows(RuntimeException.class, () -> {
            if (review.getComment() == null || review.getComment().isBlank()) {
                throw new RuntimeException("Comentário não pode ser vazio");
            }
            service.saveReview(review);
        });

        assertEquals("Comentário não pode ser vazio", exception.getMessage());
    }

    @Test
    void testCreateReviewNullProductOrUser() {
        Review review = new Review(null, null, "Comentário", 3);

        Exception exception = assertThrows(RuntimeException.class, () -> {
            if (review.getProductId() == null || review.getUserId() == null) {
                throw new RuntimeException("ProductId e UserId não podem ser nulos");
            }
            service.saveReview(review);
        });

        assertEquals("ProductId e UserId não podem ser nulos", exception.getMessage());
    }
}
