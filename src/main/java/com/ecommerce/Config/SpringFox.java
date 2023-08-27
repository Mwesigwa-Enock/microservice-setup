package com.ecommerce.Config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SpringFox {
    @Bean
    public Docket api(){
        return new Docket(DocumentationType.OAS_30)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.ecommerce"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(getApiInformation());
    }

    private ApiInfo getApiInformation() {
        return new ApiInfoBuilder()
                .title("Order Service Solution")
                .description("This is a sample implementation of the Order Service Solution")
                .version("1.0")
                .build();
    }

}
