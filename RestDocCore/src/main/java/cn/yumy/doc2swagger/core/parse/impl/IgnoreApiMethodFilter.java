package cn.yumy.doc2swagger.core.parse.impl;

import cn.yumy.doc2swagger.core.annotations.IgnoreApi;
import cn.yumy.doc2swagger.core.parse.IMethodFilter;
import cn.yumy.doc2swagger.core.parse.utils.RuntimeJavadocUtils;
import com.github.therapi.runtimejavadoc.MethodJavadoc;
import com.github.therapi.runtimejavadoc.OtherJavadoc;
import com.github.therapi.runtimejavadoc.RuntimeJavadoc;
import com.google.auto.service.AutoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;

@AutoService(IMethodFilter.class)
public class IgnoreApiMethodFilter implements IMethodFilter {
    private static Logger _logger = LoggerFactory.getLogger(IgnoreApiMethodFilter.class);

    @Override
    public boolean isSupport(Method method) {
        if (method.isAnnotationPresent(IgnoreApi.class))
        {
            _logger.debug("ignore method: {}:{}", method.getDeclaringClass(), method.getName());
            return false;
        }
        MethodJavadoc methodJavadoc = RuntimeJavadoc.getJavadoc(method);
        if (methodJavadoc.getOther() != null)
        {
            OtherJavadoc ignoreApiJavadoc = RuntimeJavadocUtils.getTag(method, "ignoreApi");
            if (ignoreApiJavadoc != null)
                return false;
        }
        return true;
    }
}
