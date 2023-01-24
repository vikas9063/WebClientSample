package com.eidiko.WebClientSample;

import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.util.InsecureTrustManagerFactory;
import jakarta.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;

import javax.net.ssl.SSLException;

@SpringBootApplication
public class WebClientSampleApplication {


	@Bean
	public WebClient webClient() throws SSLException {

		SslContext sslContext = SslContextBuilder.forClient()
				.trustManager(InsecureTrustManagerFactory.INSTANCE)
				.build();
		HttpClient httpClient =HttpClient.create().secure(t -> t.sslContext(sslContext));

		return WebClient.builder().baseUrl("https://localhost:9595/api/v1/auth")
				.defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
				.clientConnector(new ReactorClientHttpConnector(httpClient))
				.build();
	}

	public static void main(String[] args) {
		SpringApplication.run(WebClientSampleApplication.class, args);
	}

}
