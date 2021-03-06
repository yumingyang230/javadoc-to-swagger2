package cn.yumy.doc2swagger.spring.parameter.parser;

import cn.yumy.doc2swagger.core.config.ExtOrder;
import cn.yumy.doc2swagger.core.models.ParameterModel;
import cn.yumy.doc2swagger.core.parse.IMethodParameterParser;
import cn.yumy.doc2swagger.core.parse.impl.AbstractMethodParameterParser;
import com.google.auto.service.AutoService;
import lombok.var;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.Part;
import java.lang.reflect.Parameter;
import java.lang.reflect.Type;

@AutoService(IMethodParameterParser.class)
@ExtOrder(100)
public class SpringMultipartParameterParser extends AbstractMethodParameterParser {

    @Override
    protected ParameterModel.ParameterLocation getParameterLocation(Parameter parameter, Type actualParamType) {
        return ParameterModel.ParameterLocation.FILE;
    }

    @Override
    protected boolean isRequired(Parameter parameter, Type actualParamType) {
        var requestParamAnno = AnnotatedElementUtils.getMergedAnnotation(parameter, RequestParam.class);
        if (requestParamAnno != null)
        {
            return requestParamAnno.required();
        }
        return true;
    }

    @Override
    public boolean isSupport(Parameter parameter) {
        if (MultipartFile.class.isAssignableFrom(parameter.getType())
            || Part.class.isAssignableFrom(parameter.getType()))
        {
            return true;
        }
        return false;
    }

    @Override
    protected String getParameterName(Parameter parameter) {
        var paramName = super.getParameterName(parameter);

        var requestParamAnno = AnnotatedElementUtils.getMergedAnnotation(parameter, RequestParam.class);
        if (requestParamAnno != null)
        {
            return requestParamAnno.name();
        }
        return paramName;
    }
}
