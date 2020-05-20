package cn.nyhlw.doc2swagger.core.parse;

import cn.nyhlw.doc2swagger.core.models.PathModel;
import com.github.therapi.runtimejavadoc.MethodJavadoc;

import java.lang.reflect.Method;

public interface IMethodParser {
    /**
     * 解析Method到Path
     */
    PathModel parse(Method method, MethodJavadoc methodJavadoc, PathModel pathModel);
}
