package com.review.service.api;

import com.review.service.model.Review;
import com.review.service.repository.ReviewRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class ReviewApiTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ReviewRepository repository;

    private Review review;

    @BeforeEach
    void setup() {
        repository.deleteAll(); // garante ambiente limpo
        review = new Review("produto123", "usuario123", "Ótimo produto!", 5);
        repository.save(review);
    }

    @Test
    void testGetAllReviews() throws Exception {
        mockMvc.perform(get("/reviews"))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$").isArray())
               .andExpect(jsonPath("$[0].comment").value("Ótimo produto!"));
    }

    @Test
    void testGetReviewById() throws Exception {
        mockMvc.perform(get("/reviews/" + review.getId()))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.id").value(review.getId()))
               .andExpect(jsonPath("$.comment").value("Ótimo produto!"));
    }
}
