package cn.nyhlw.doc2swagger.spring.examples;

import cn.nyhlw.doc2swagger.core.config.RestDocConfig;
import cn.nyhlw.doc2swagger.spring.examples.ext.TestSwaggerFilter;
import cn.nyhlw.doc2swagger.springswagger2.EnableSwagger2;
import cn.nyhlw.doc2swagger.swagger.common.SwaggerUIConfiguration;
import cn.nyhlw.doc2swagger.swagger2.RestDocConfigSwagger2Ext;


import lombok.var;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;

@EnableSwagger2
//@EnableSwagger3
@SpringBootApplication
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class);
    }

    @Bean
    RestDocConfig _swaggerConfig()
    {
        return RestDocConfig.builder()
                .apiTitle("rest doc title")
                .apiDescription("rest doc desc")
                .apiVersion("api version")
                .fieldPrefix("_")
//                .tagDescriptionAsName(true)
                .hideEmptyController(true)
                .resolveJavaDocAsTypeName(false)
//                .httpBasicAuth(new RestDocConfig.HttpBasicAuth("restdoc","restdoc"))
                .packages(Arrays.asList("cn.nyhlw.doc2swagger.spring.examples"))
                .servers(Arrays.asList(RestDocConfig.Server.builder().description("url desc").url("https://localhost:8084").build(),
                        RestDocConfig.Server.builder().description("second").url("http://localhost:8084").build()))
                .build();
    }
//    @Bean
//    RestDocConfigSwagger3Ext restDocConfigSwagger3Ext()
//    {
//        return RestDocConfigSwagger3Ext.builder()
//                .openAPIFilters(Arrays.asList(new TestOpenAPIFilter(), new BearerOpenAPIFilter()))
//                .build();
//    }
    @Bean
    RestDocConfigSwagger2Ext restDocConfigSwagger2Ext()
    {
        return RestDocConfigSwagger2Ext.builder()
                .swaggerFilters(Arrays.asList(new TestSwaggerFilter()))
                .build();
    }
    @Bean
    SwaggerUIConfiguration _swaggerUIConfiguration()
    {
        var uiConfig = new SwaggerUIConfiguration();
//        uiConfig.setDefaultModelRendering("model");
        uiConfig.setDefaultModelExpandDepth(100);
//        uiConfig.setDocExpansion("full");
        return uiConfig;
    }




}
