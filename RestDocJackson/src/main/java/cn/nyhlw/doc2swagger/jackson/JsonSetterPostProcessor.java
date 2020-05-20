package cn.nyhlw.doc2swagger.jackson;

import cn.nyhlw.doc2swagger.core.models.PropertyModel;
import cn.nyhlw.doc2swagger.core.models.TypeContext;
import cn.nyhlw.doc2swagger.core.parse.postprocessor.IPropertyPostProcessor;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.google.auto.service.AutoService;

/**
 * com.fasterxml.jackson.annotation.JsonSetter
 */
@AutoService(IPropertyPostProcessor.class)
public class JsonSetterPostProcessor implements IPropertyPostProcessor {
    @Override
    public PropertyModel postProcess(PropertyModel propertyModel, TypeContext typeContext) {
        if (typeContext.getParameter() == null) // == null 表示是返回值，不需要处理该注解
            return propertyModel;
        JsonSetter jsonSetterAnno = propertyModel.getPropertyItem().getAnnotation(JsonSetter.class);
        if (jsonSetterAnno == null)
            return propertyModel;

        if (!jsonSetterAnno.value().isEmpty())
        {
            propertyModel.setName(jsonSetterAnno.value());
        }
        return propertyModel;
    }
}
