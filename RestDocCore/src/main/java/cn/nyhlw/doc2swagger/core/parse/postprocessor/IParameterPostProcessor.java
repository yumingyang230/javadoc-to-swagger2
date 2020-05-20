package cn.nyhlw.doc2swagger.core.parse.postprocessor;

import cn.nyhlw.doc2swagger.core.models.ParameterModel;

import java.lang.reflect.Parameter;

public interface IParameterPostProcessor {
    ParameterModel postProcess(ParameterModel model, Parameter parameter);
}
