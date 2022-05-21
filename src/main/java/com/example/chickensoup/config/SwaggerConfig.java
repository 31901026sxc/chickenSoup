package com.example.chickensoup.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@ComponentScan(basePackages = {"com.example.examsys.Controller"})//扫描的包路径
public class SwaggerConfig {
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .paths(PathSelectors.any())
                .build();
    }

    /**
     * 该套 API 说明，包含作者、简介、版本、host、服务URL
     *
     * @return
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("api 说明")//接口标题
                .description("教考分离系统接口群")//接口描述
                .version("v1.0")//版本号
                .contact(new Contact("zwt", "http://tipsynovember.xyz/", "729009583@qq.com"))//联系人信息
                .build();
    }
}