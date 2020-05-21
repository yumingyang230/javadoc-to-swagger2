package cn.yumy.doc2swagger.swagger2;

import cn.yumy.doc2swagger.core.config.RestDocParseConfig;
import cn.yumy.doc2swagger.core.utils.ServiceLoaders;
import cn.yumy.doc2swagger.swagger.common.SwaggerGeneratorConfig;
import lombok.Data;

import java.util.List;

@Data
public class Swagger2GeneratorConfig extends SwaggerGeneratorConfig {
    private List<ISwaggerFilter> _swaggerFilters;

    public Swagger2GeneratorConfig(RestDocParseConfig parseConfig) {
        super(parseConfig);

        _swaggerFilters = ServiceLoaders.loadServices(ISwaggerFilter.class, parseConfig);
    }
}
