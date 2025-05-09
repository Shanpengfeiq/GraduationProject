package com.example.chat.config;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class WebClientUtil {
    private final WebClient webClient;

    public WebClientUtil() {
        this.webClient = WebClient.builder()
                .baseUrl("http://localhost:8034")
                .build();
    }

    public WebClient getWebClient() {
        return webClient;
    }
}
