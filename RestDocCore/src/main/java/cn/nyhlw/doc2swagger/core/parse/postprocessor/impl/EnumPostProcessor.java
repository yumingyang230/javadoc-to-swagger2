package cn.nyhlw.doc2swagger.core.parse.postprocessor.impl;

import cn.nyhlw.doc2swagger.core.config.AbstractRestDocParseConfigAware;
import cn.nyhlw.doc2swagger.core.models.ParameterModel;
import cn.nyhlw.doc2swagger.core.models.PropertyModel;
import cn.nyhlw.doc2swagger.core.models.ResponseModel;
import cn.nyhlw.doc2swagger.core.models.TypeContext;
import cn.nyhlw.doc2swagger.core.parse.postprocessor.IParameterPostProcessor;
import cn.nyhlw.doc2swagger.core.parse.postprocessor.IPropertyPostProcessor;
import cn.nyhlw.doc2swagger.core.parse.postprocessor.IResponsePostProcessor;
import cn.nyhlw.doc2swagger.core.parse.utils.FormatUtils;
import cn.nyhlw.doc2swagger.core.parse.utils.ReflectUtils;
import com.github.therapi.runtimejavadoc.RuntimeJavadoc;
import com.google.auto.service.AutoService;
import lombok.var;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@AutoService({IPropertyPostProcessor.class, IParameterPostProcessor.class, IResponsePostProcessor.class})
public class EnumPostProcessor extends AbstractRestDocParseConfigAware implements IPropertyPostProcessor, IParameterPostProcessor, IResponsePostProcessor {

    @Override
    public ParameterModel postProcess(ParameterModel model, Parameter parameter) {
        if (!ReflectUtils.isEnum(model.getParameterType()))
            return model;

        model.setEnums(getEnums(model.getParameterType()));
        model.setDescription(getDesc(model.getParameterType()));
        return model;
    }

    @Override
    public PropertyModel postProcess(PropertyModel propertyModel, TypeContext typeContext) {
        if (!ReflectUtils.isEnum(propertyModel.getPropertyType()))
            return propertyModel;
        propertyModel.setEnums(getEnums(propertyModel.getPropertyType()));
        propertyModel.setDescription(getDesc(propertyModel.getPropertyType()));
        return propertyModel;
    }

    @Override
    public ResponseModel postProcess(ResponseModel model, Method method) {
        if (!ReflectUtils.isEnum(model.getReturnModel().getReturnType()))
            return model;
        model.getReturnModel().setEnums(getEnums(model.getReturnModel().getReturnType()));
        model.getReturnModel().setDescription(getDesc(model.getReturnModel().getReturnType()));
        return model;
    }

    private String getDesc(Type type)
    {
        if (!(type instanceof Class))
            return null;
        Class clazz = (Class) type;
        var enumDoc = RuntimeJavadoc.getJavadoc(clazz);

        String enumStr = "";
        for (var enumConst : enumDoc.getEnumConstants()) {
            if (!enumStr.isEmpty())
                enumStr += ", ";
            enumStr += enumConst.getName();
            String desc = FormatUtils.format(enumConst.getComment());
            if (desc != null && !desc.isEmpty()) {
                enumStr += ": " + desc;
            }
        }

        String desc = FormatUtils.format(enumDoc.getComment());

        if (desc == null || desc.isEmpty())
            return enumStr;
        else
            return desc + "; " + enumStr;
    }
    private List<String> getEnums(Type type)
    {
        if (!(type instanceof Class))
            return null;
        Class clazz = (Class) type;
        return Arrays.stream(clazz.getEnumConstants()).map(o -> o.toString()).collect(Collectors.toList());
    }
}
