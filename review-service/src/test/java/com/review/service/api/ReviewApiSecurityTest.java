package com.review.service.api;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ReviewApiSecurityTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testUnauthorizedAccess() throws Exception {
        // Simula requisição sem autenticação
        mockMvc.perform(get("/reviews/1"))
               .andExpect(status().isUnauthorized());
    }

    @Test
    void testAuthorizedAccess() throws Exception {
        // Exemplo conceitual: mock de token
        mockMvc.perform(get("/reviews/1").header("Authorization", "Bearer fakeToken"))
               .andExpect(status().isOk());
    }
}
