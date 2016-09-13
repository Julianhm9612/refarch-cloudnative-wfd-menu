package com.ibm.microservices.wfd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import com.google.common.base.Predicate;
import static springfox.documentation.builders.PathSelectors.regex;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@EnableDiscoveryClient
public class MenuApplication {

	public static void main(String[] args) {
		SpringApplication.run(MenuApplication.class, args);
		System.out.println("Running "+MenuApplication.class+" via Spring Boot!");
	}

	@Bean
	@LoadBalanced
	public RestTemplate restTemplate() {
    return new RestTemplate();
	}

		@Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .paths(regex("/menu"))
                .build();
    }
		private ApiInfo apiInfo() {
		 return new ApiInfoBuilder()
						 .title("What's for dinner API")
						 .description("What's for dinner reference application API description using Swagger 2.0")
						 .termsOfServiceUrl("http://springfox.io")
						 .contact("Rick Osowski and Jesus Almaraz")
						 .license("Apache License Version 2.0")
						 .licenseUrl("https://github.com/springfox/springfox/blob/master/LICENSE")
						 .version("2.0")
						 .build();
 }
}
