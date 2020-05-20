package cn.nyhlw.doc2swagger.core.parse.postprocessor;

import cn.nyhlw.doc2swagger.core.models.ResponseModel;

import java.lang.reflect.Method;

public interface IResponsePostProcessor {
    ResponseModel postProcess(ResponseModel model, Method method);
}
