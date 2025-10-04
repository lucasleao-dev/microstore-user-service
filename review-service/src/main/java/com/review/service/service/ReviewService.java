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

    /**
     * Salva um review no banco.
     * @param review Review a ser salvo
     * @return Review salvo com ID preenchido
     */
    public Review saveReview(Review review) {
        return repository.save(review);
    }

    /**
     * Retorna todos os reviews de um produto específico.
     * @param productId ID do produto
     * @return Lista de reviews do produto
     */
    public List<Review> getReviewsByProduct(String productId) {
        return repository.findByProductId(productId);
    }

    /**
     * Retorna todos os reviews cadastrados.
     * @return Lista de todos os reviews
     */
    public List<Review> getAllReviews() {
        return repository.findAll();
    }

    /**
     * Retorna um review pelo ID.
     * @param id ID do review
     * @return Review encontrado
     * @throws RuntimeException se não encontrar
     */
    public Review getReviewById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Review not found"));
    }

    /**
     * Atualiza um review existente.
     * @param id ID do review a ser atualizado
     * @param updatedReview Review com os dados novos
     * @return Review atualizado
     */
    public Review updateReview(Long id, Review updatedReview) {
        Review existing = getReviewById(id);
        existing.setComment(updatedReview.getComment());
        existing.setRating(updatedReview.getRating());
        existing.setProductId(updatedReview.getProductId());
        existing.setUserId(updatedReview.getUserId());
        return repository.save(existing);
    }

    /**
     * Remove um review pelo ID.
     * @param id ID do review a ser removido
     */
    public void deleteReview(Long id) {
        Review existing = getReviewById(id);
        repository.delete(existing);
    }
}
