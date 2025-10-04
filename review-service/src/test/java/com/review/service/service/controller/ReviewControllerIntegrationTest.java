package com.review.service.service.controller;

import com.review.service.model.Review;
import com.review.service.repository.ReviewRepository;
import com.review.service.service.ReviewService;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ReviewServiceMockTest {

    private final ReviewRepository repository = mock(ReviewRepository.class);
    private final ReviewService service = new ReviewService(repository);

    @Test
    void testCreateReview() {
        // Criando review usando o construtor correto
        Review review = new Review("produto123", "usuario123", "Ótimo produto!", 5);

        // Mockando o comportamento do repository
        Review savedReview = new Review(1L, "produto123", "usuario123", "Ótimo produto!", 5, LocalDateTime.now());
        when(repository.save(review)).thenReturn(savedReview);

        // Chamando o service
        Review created = service.saveReview(review);

        // Verificações
        assertNotNull(created.getId()); 
        assertEquals("Ótimo produto!", created.getComment());
        verify(repository, times(1)).save(review);
    }

    @Test
    void testFindByIdNotFound() {
        // Mockando findById com Optional vazio
        when(repository.findById(1L)).thenReturn(Optional.empty());

        // Como getReviewById ainda não existe no service, adicionamos ele no service:
        // public Review getReviewById(Long id) {
        //     return repository.findById(id).orElseThrow(() -> new RuntimeException("Review não encontrado"));
        // }

        assertThrows(RuntimeException.class, () -> service.getReviewById(1L));
    }
}
