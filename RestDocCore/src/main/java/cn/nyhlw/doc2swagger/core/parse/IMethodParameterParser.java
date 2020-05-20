package cn.nyhlw.doc2swagger.core.parse;

import cn.nyhlw.doc2swagger.core.models.ParameterModel;
import com.github.therapi.runtimejavadoc.ParamJavadoc;

import java.lang.reflect.Parameter;

public interface IMethodParameterParser {
    ParameterModel parse(Parameter parameter, ParamJavadoc paramJavadoc, ParameterModel parameterModel);
    boolean isSupport(Parameter parameter);
}
