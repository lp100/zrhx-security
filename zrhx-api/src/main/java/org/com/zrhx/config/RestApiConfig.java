package org.com.zrhx.config;
import io.swagger.annotations.Api;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;


import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

@EnableWebMvc
@EnableSwagger2
@ComponentScan(basePackages = {"org.com.zrhx.api"})
@Configuration
public class RestApiConfig extends WebMvcConfigurationSupport{

    @Bean
    public Docket createRestApi() {
        List<Parameter> operationParameters = new ArrayList<>();
        Parameter  operationParameter = new ParameterBuilder()
         .name("token")
         .description(
         "密钥")
         .modelRef(new ModelRef("string"))
         .parameterType("query").required(true).build();

        Parameter  headeroperationParameter = new ParameterBuilder()
                .name("headertoken")
                .description(
                        "请求头")
                .modelRef(new ModelRef("string"))
                .parameterType("header").required(true).build();

        operationParameters.add(operationParameter);
        operationParameters.add(headeroperationParameter);
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .globalOperationParameters(operationParameters)
                .select() // 选择那些路径和api会生成document
                .apis(RequestHandlerSelectors.withClassAnnotation(Api.class))
                .paths(PathSelectors.any())// 对所有路径进行监控
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("接口文档")
                .version("1.1")
                .build();
    }
}
