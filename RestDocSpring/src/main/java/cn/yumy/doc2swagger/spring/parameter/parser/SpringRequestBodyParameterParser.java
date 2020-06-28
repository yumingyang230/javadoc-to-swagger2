package cn.yumy.doc2swagger.spring.parameter.parser;

import cn.yumy.doc2swagger.core.config.ExtOrder;
import cn.yumy.doc2swagger.core.models.ParameterModel;
import cn.yumy.doc2swagger.core.parse.IMethodParameterParser;
import cn.yumy.doc2swagger.core.parse.impl.AbstractMethodParameterParser;
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
//        return AnnotatedElementUtils.hasAnnotation(parameter, RequestBody.class);
//        判断非字符串类型、数字类型、布尔类型
        if (String.class.isAssignableFrom(parameter.getType()) ||
                Number.class.isAssignableFrom(parameter.getType()) ||
                Boolean.class.isAssignableFrom(parameter.getType())
        ) {
            //不是对象类型
            return false;
        }
        return true;
    }
}
