package com.alura.literalura.service;

import com.alura.literalura.dto.GutendexResponseDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class GutendexClient {
    private final WebClient webClient;
    private final String apiUrl;

    public GutendexClient(WebClient webClient, @Value("${gutendex.api.url}") String apiUrl) {
        this.webClient = webClient;
        this.apiUrl = apiUrl;
    }

    public GutendexResponseDTO searchBook(String title) {
        return webClient.get()
                .uri(apiUrl + "?search=" + title)
                .retrieve()
                .bodyToMono(GutendexResponseDTO.class)
                .block();
    }
}