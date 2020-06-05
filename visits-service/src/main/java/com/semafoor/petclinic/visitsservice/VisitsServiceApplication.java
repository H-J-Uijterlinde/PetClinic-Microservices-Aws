package com.semafoor.petclinic.visitsservice;

import com.semafoor.petclinic.visitsservice.service.RestTemplateNotFoundErrorHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.web.client.RestTemplate;

@Slf4j
@EnableDiscoveryClient
@SpringBootApplication
public class VisitsServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(VisitsServiceApplication.class, args);
	}

	@Bean
	@LoadBalanced
	RestTemplate restTemplate() {

		ClientHttpRequestInterceptor interceptor = (HttpRequest request, byte[] body,
		ClientHttpRequestExecution execution) -> {
			log.info("Request to URI {}, with HTTP verb {}",
					request.getURI(), request.getMethod().toString());
			return execution.execute(request, body);
		};

		return new RestTemplateBuilder()
				.additionalInterceptors(interceptor)
				.errorHandler(new RestTemplateNotFoundErrorHandler())
				.build();
	}
}
