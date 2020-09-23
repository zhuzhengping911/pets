package com.animal.user.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.reactive.function.client.WebClientAutoConfiguration;
import org.springframework.boot.web.reactive.function.client.WebClientCustomizer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

/**
 * @Author: Zhengping Zhu
 * @Date: 2020/9/23 2:00 PM
 * @description: com.animal.user.controller
 * @version: v1.0
 */
@Slf4j
@RestController
@RequestMapping("/webclient")
public class WebClientController {

    @Autowired
    private WebClient.Builder webClientBuilder;

    @GetMapping("/test")
    public Mono<String> test() {
        Mono<String> result = webClientBuilder.build()
                .get()
                .uri("http://discovery-server/hello?name=zzp")
                .retrieve()
                .bodyToMono(String.class);
        return result;
    }
}
