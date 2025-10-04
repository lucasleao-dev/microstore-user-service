package com.review.service.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "reviews")
public class Review {

    public Review(Long id, String productId, String userId, String comment, int rating, LocalDateTime createdAt) {
		super();
		this.id = id;
		this.productId = productId;
		this.userId = userId;
		this.comment = comment;
		this.rating = rating;
		this.createdAt = createdAt;
	}
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String productId; // ID do produto
    private String userId;    // ID do usuário
    private String comment;
    private int rating;       // nota de 1 a 5
    private LocalDateTime createdAt = LocalDateTime.now();

    // Construtor padrão (necessário para JPA)
    public Review() {}

    // Construtor útil para testes
    public Review(String productId, String userId, String comment, int rating) {
        this.productId = productId;
        this.userId = userId;
        this.comment = comment;
        this.rating = rating;
        this.createdAt = LocalDateTime.now();
    }

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getProductId() { return productId; }
    public void setProductId(String productId) { this.productId = productId; }
    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }
    public String getComment() { return comment; }
    public void setComment(String comment) { this.comment = comment; }
    public int getRating() { return rating; }
    public void setRating(int rating) { this.rating = rating; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}
