package cn.nyhlw.doc2swagger.spring.parameter.parser;

import cn.nyhlw.doc2swagger.core.config.ExtOrder;
import cn.nyhlw.doc2swagger.core.models.ParameterModel;
import cn.nyhlw.doc2swagger.core.parse.IMethodParameterParser;
import cn.nyhlw.doc2swagger.core.parse.impl.AbstractMethodParameterParser;
import com.google.auto.service.AutoService;
import lombok.var;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.web.bind.annotation.RequestBody;

import java.lang.reflect.Parameter;
import java.lang.reflect.Type;

@AutoService(IMethodParameterParser.class)
@ExtOrder(200)
public class SpringRequestBodyParameterParser extends AbstractMethodParameterParser {

    @Override
    protected ParameterModel.ParameterLocation getParameterLocation(Parameter parameter, Type actualParamType) {
        return ParameterModel.ParameterLocation.BODY;
    }

    @Override
    protected boolean isRequired(Parameter parameter, Type actualParamType) {
        var requestBodyAnno = AnnotatedElementUtils.getMergedAnnotation(parameter, RequestBody.class);
        if (requestBodyAnno != null)
        {
            return requestBodyAnno.required();
        }
        return true;
    }

    @Override
    public boolean isSupport(Parameter parameter) {
        return AnnotatedElementUtils.hasAnnotation(parameter, RequestBody.class);
    }
}
