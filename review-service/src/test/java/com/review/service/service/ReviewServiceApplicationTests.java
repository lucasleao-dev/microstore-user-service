package com.review.service.service;

import com.review.service.model.Review;
import com.review.service.repository.ReviewRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ReviewServiceMockTest {

    @Mock
    private ReviewRepository repository;

    @InjectMocks
    private ReviewService service;

    @Test
    void testGetReviewsByProduct() {
        // Criando review mock
        Review review1 = new Review();
        review1.setProductId("p1");
        review1.setComment("Bom produto");
        review1.setRating(5);

        // Definindo comportamento do mock
        when(repository.findByProductId("p1")).thenReturn(List.of(review1));

        // Chamando método do service
        List<Review> reviews = service.getReviewsByProduct("p1");

        // Verificando resultados
        assertEquals(1, reviews.size());
        assertEquals("Bom produto", reviews.get(0).getComment());
        assertEquals(5, reviews.get(0).getRating());

        // Verificando se o repository foi chamado
        verify(repository, times(1)).findByProductId("p1");
    }

    @Test
    void testGetAllReviews() {
        Review review1 = new Review();
        review1.setProductId("p1");
        review1.setComment("Ótimo");
        review1.setRating(4);

        Review review2 = new Review();
        review2.setProductId("p2");
        review2.setComment("Regular");
        review2.setRating(3);

        when(repository.findAll()).thenReturn(List.of(review1, review2));

        List<Review> reviews = service.getAllReviews();

        assertEquals(2, reviews.size());
        assertEquals("Ótimo", reviews.get(0).getComment());
        assertEquals("Regular", reviews.get(1).getComment());

        verify(repository, times(1)).findAll();
    }

    @Test
    void testSaveReview() {
        Review review = new Review();
        review.setProductId("p1");
        review.setComment("Excelente");
        review.setRating(5);

        when(repository.save(review)).thenReturn(review);

        Review saved = service.saveReview(review);

        assertEquals("Excelente", saved.getComment());
        assertEquals(5, saved.getRating());

        verify(repository, times(1)).save(review);
    }
}
