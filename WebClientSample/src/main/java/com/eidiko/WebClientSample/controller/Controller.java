package com.eidiko.WebClientSample.controller;


import com.eidiko.WebClientSample.modal.Post;
import com.eidiko.WebClientSample.modal.Request;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1")
public class Controller {

    @Autowired
    private WebClient webClient;

//    @PostConstruct
//    public void init(){
//        webClient= WebClient.builder().baseUrl("https://reqres.in/api/products")
//                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE).build();
//    }

    @PostMapping("/get-all-posts")
    public Mono<String> getPosts(@RequestBody Request request){

        return webClient.post().uri("/login").bodyValue(request).retrieve().bodyToMono(String.class);


    }


}
