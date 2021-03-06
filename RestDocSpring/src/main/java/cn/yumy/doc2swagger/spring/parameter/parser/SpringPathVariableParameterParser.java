package cn.yumy.doc2swagger.spring.parameter.parser;

import cn.yumy.doc2swagger.core.config.ExtOrder;
import cn.yumy.doc2swagger.core.models.ParameterModel;
import cn.yumy.doc2swagger.core.parse.IMethodParameterParser;
import cn.yumy.doc2swagger.core.parse.impl.AbstractMethodParameterParser;
import com.google.auto.service.AutoService;
import lombok.var;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;

import java.lang.reflect.Parameter;
import java.lang.reflect.Type;

@AutoService(IMethodParameterParser.class)
@ExtOrder(300)
public class SpringPathVariableParameterParser extends AbstractMethodParameterParser {

    @Override
    protected ParameterModel.ParameterLocation getParameterLocation(Parameter parameter, Type actualParamType) {
        return ParameterModel.ParameterLocation.PATH;
    }

    @Override
    protected boolean isRequired(Parameter parameter, Type actualParamType) {
        return parameter.getAnnotation(PathVariable.class).required();
    }

    @Override
    public boolean isSupport(Parameter parameter) {
        return AnnotatedElementUtils.hasAnnotation(parameter, PathVariable.class);
    }

    @Override
    protected String getParameterName(Parameter parameter) {
        var paramName = super.getParameterName(parameter);
        var requestParamAnno = AnnotatedElementUtils.getMergedAnnotation(parameter, PathVariable.class);
        if (requestParamAnno != null && !StringUtils.isEmpty(requestParamAnno.name()))
        {
            return requestParamAnno.name();
        }
        return paramName;
    }
}
