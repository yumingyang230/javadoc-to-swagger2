package cn.nyhlw.doc2swagger.spring.response;

import cn.nyhlw.doc2swagger.core.parse.IMethodReturnParser;
import cn.nyhlw.doc2swagger.core.parse.impl.AbstractMethodReturnParser;
import com.google.auto.service.AutoService;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.lang.reflect.Method;

@AutoService(IMethodReturnParser.class)
public class SpringResponseBodyReturnParser extends AbstractMethodReturnParser {

    @Override
    protected int parseStatusCode(Method method) {
        ResponseStatus responseStatusAnno = AnnotatedElementUtils.getMergedAnnotation(method, ResponseStatus.class);
        if (responseStatusAnno != null)
        {
            responseStatusAnno = AnnotatedElementUtils.getMergedAnnotation(method.getDeclaringClass(), ResponseStatus.class);
        }
        if (responseStatusAnno != null)
        {
            return responseStatusAnno.code() != null ? responseStatusAnno.code().value() : responseStatusAnno.value().value();
        }
        return 200;
    }
}
