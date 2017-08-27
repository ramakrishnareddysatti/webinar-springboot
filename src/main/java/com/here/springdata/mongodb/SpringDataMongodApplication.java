package com.here.springdata.mongodb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class SpringDataMongodApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringDataMongodApplication.class, args);
	}

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(new ApiInfoBuilder()
						 .title("Employee Details REST API")
						 .description("This documents provide possible opertaions which can be performed on the Employees details")
						 .version("1.0.0")
						 .license("License to Here Technologies")
						 .build())
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.here"))
				.paths(PathSelectors.any())
				.build();
	}
}
