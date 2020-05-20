package cn.nyhlw.doc2swagger.swagger2;

import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
public class RestDocConfigSwagger2Ext {
    private List<ISwaggerFilter> _swaggerFilters;

    public List<ISwaggerFilter> getSwaggerFilters()
    {
        if (_swaggerFilters == null) {
            _swaggerFilters = new ArrayList<>();
        }
        return _swaggerFilters;
    }
}
