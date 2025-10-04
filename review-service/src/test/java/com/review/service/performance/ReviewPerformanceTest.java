package com.review.service.performance;

import com.review.service.model.Review;
import com.review.service.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.IntStream;

/**
 * Testes de performance conceituais.
 * Em produção, usar JMeter, k6 ou Gatling para testes reais.
 */
@Component
public class ReviewPerformanceTest {

    @Autowired
    private ReviewRepository repository;

    /**
     * Simula centenas de requisições simultâneas para criar reviews.
     */
    public void loadTest() {
        System.out.println("Simulando centenas de requisições simultâneas...");
        IntStream.range(0, 100).parallel().forEach(i -> {
            Review review = new Review("produto" + i, "usuario" + i, "Comentário " + i, 5);
            repository.save(review);
        });
        System.out.println("Load test concluído!");
    }

    /**
     * Simula picos de tráfego extremos (stress test)
     */
    public void stressTest() {
        System.out.println("Simulando picos de tráfego para testar limites do sistema...");
        IntStream.range(0, 1000).parallel().forEach(i -> {
            Review review = new Review("produtoStress" + i, "usuarioStress" + i, "Comentário Stress " + i, 5);
            repository.save(review);
        });
        System.out.println("Stress test concluído!");
    }
}
