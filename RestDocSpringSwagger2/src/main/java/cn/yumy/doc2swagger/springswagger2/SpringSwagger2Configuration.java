package cn.yumy.doc2swagger.springswagger2;

import cn.yumy.doc2swagger.core.config.RestDocConfig;
import cn.yumy.doc2swagger.core.parse.IRestDocParser;
import cn.yumy.doc2swagger.core.parse.impl.JavaTypeInspector;
import cn.yumy.doc2swagger.core.parse.impl.RestDocParser;
import cn.yumy.doc2swagger.swagger.common.TypeNameParser;
import cn.yumy.doc2swagger.spring.SpringControllerResolver;
import cn.yumy.doc2swagger.spring.SpringRestDocParseConfig;
import cn.yumy.doc2swagger.spring.filter.HttpBasicAuthFilter;
import cn.yumy.doc2swagger.swagger.common.PrimitiveSwaggerTypeInspector;
import cn.yumy.doc2swagger.swagger.common.SwaggerGeneratorConfig;
import cn.yumy.doc2swagger.swagger.common.SwaggerUIConfiguration;
import cn.yumy.doc2swagger.swagger2.*;
import lombok.var;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Configuration
public class SpringSwagger2Configuration {

    @Bean("swagger2")
    IRestDocParser _docParser(@Autowired(required = false) RestDocConfig restDocConfig,
                              @Autowired(required = false) RestDocConfigSwagger2Ext ext) {
        if (restDocConfig == null) {
            restDocConfig = RestDocConfig.builder()
                    .apiDescription("API descritpion")
                    .apiTitle("API title")
                    .apiVersion("1.0-SNAPSHOT")
                    .packages(Arrays.asList(""))
//                    .servers(Arrays.asList(RestDocConfig.Server.builder().url("localhost:8080").description("local").build()))
                    .build();
        }
        var parseConfig = new SpringRestDocParseConfig();

        parseConfig.getControllerResolvers().add(new SpringControllerResolver(restDocConfig.getPackages()));


        // todo 从spring容器中获取实例
        var docConfig = new Swagger2GeneratorConfig(parseConfig);
        docConfig.setDescription(restDocConfig.getApiDescription());
        docConfig.setTitle(restDocConfig.getApiTitle());
        docConfig.setVersion(restDocConfig.getApiVersion());
        docConfig.setServers(convertServers(restDocConfig.getServers()));
        docConfig.setSwaggerTypeInspector(new PrimitiveSwaggerTypeInspector());
        docConfig.setTypeInspector(new JavaTypeInspector());
        docConfig.setTypeNameParser(new TypeNameParser());

        if (restDocConfig.isTagDescriptionAsName()) {
            List<ISwaggerFilter> swaggerFilters = new ArrayList<>(ext.getSwaggerFilters());
            swaggerFilters.add(new TagDescriptionAsNameSwaggerFilter());
            ext.setSwaggerFilters(swaggerFilters);
        }
        if (restDocConfig.isHideEmptyController()) {
            List<ISwaggerFilter> swaggerFilters = new ArrayList<>(ext.getSwaggerFilters());
            swaggerFilters.add(new HideEmptyControllerSwaggerFilter());
            ext.setSwaggerFilters(swaggerFilters);
        }
        if (ext != null) {
            docConfig.setSwaggerFilters(ext.getSwaggerFilters());
        }

        parseConfig.setRestDocGenerator(new Swagger2RestDocGenerator(docConfig));
        parseConfig.setFieldPrefix(restDocConfig.getFieldPrefix());
        return new RestDocParser(parseConfig);
    }

    private List<SwaggerGeneratorConfig.ServerInfo> convertServers(List<RestDocConfig.Server> servers) {
        if (servers == null || servers.size() <= 0) {
            // If the host is not included, the host serving the documentation is to be used (including the port).
            return new ArrayList<>();
        }
        List<SwaggerGeneratorConfig.ServerInfo> serverInfos = new ArrayList<>();
        for (RestDocConfig.Server server : servers) {
            SwaggerGeneratorConfig.ServerInfo serverInfo =
                    SwaggerGeneratorConfig.ServerInfo.builder().description(server.getDescription()).url(server.getUrl())
                            .build();

            serverInfos.add(serverInfo);
        }
        return serverInfos;
    }

    @Bean
    SpringSwagger2Controller _springSwagger2Controller(@Qualifier("swagger2") IRestDocParser docParser) {
        SwaggerUIConfiguration uiConfiguration;
        try {
            uiConfiguration = _applicationContext.getBean(SwaggerUIConfiguration.class);
        } catch (NoSuchBeanDefinitionException e) {
            uiConfiguration = new SwaggerUIConfiguration();
        }
        var controller = new SpringSwagger2Controller(docParser, uiConfiguration);
        return controller;
    }

    @Bean
    @ConditionalOnClass(FilterRegistrationBean.class)
    @ConditionalOnMissingBean(RestDocConfig.HttpBasicAuth.class)
    public FilterRegistrationBean<HttpBasicAuthFilter> swagger2HttpFilter(@Autowired(required = false) RestDocConfig restDocConfig) {
        RestDocConfig.HttpBasicAuth httpBasicAuth;
        if (restDocConfig == null || (httpBasicAuth = restDocConfig.getHttpBasicAuth()) == null)
            httpBasicAuth = new RestDocConfig.HttpBasicAuth(null, null);

        FilterRegistrationBean<HttpBasicAuthFilter> filterBean = new FilterRegistrationBean<>();
        HttpBasicAuthFilter authFilter = new HttpBasicAuthFilter(httpBasicAuth.getUsername(), httpBasicAuth.getPassword());
        filterBean.addUrlPatterns("/swagger2-ui/**", "/swagger2.json", "/swagger-ui/*", "/swagger.json");
        filterBean.setFilter(authFilter);
        return filterBean;
    }

    @Autowired
    ApplicationContext _applicationContext;
}
