package com.kwdz.blog.svc.common.swagger;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 接口文档API
 */
@ConditionalOnProperty(name = "swagger.enable", havingValue = "true")
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    /**
     * 添加摘要信息(Docket)
     */
    @Bean
    public Docket controllerApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(new ApiInfoBuilder()
                        .title("标题：个人Blog接口文档（svc端）")
                        .description("描述：个人Blog接口")
                        .contact(new Contact("YT.Hu", null, null))
                        .version("版本号:0.1")
                        .build())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.kwdz"))
                .paths(PathSelectors.any())
                .build();
    }
}