package cn.nyhlw.doc2swagger.core.parse.postprocessor.impl;

import cn.nyhlw.doc2swagger.core.models.PropertyModel;
import cn.nyhlw.doc2swagger.core.models.TypeContext;
import cn.nyhlw.doc2swagger.core.parse.postprocessor.IPropertyPostProcessor;
import com.google.auto.service.AutoService;

@AutoService(IPropertyPostProcessor.class)
public class RequiredPropertyPostProcessor implements IPropertyPostProcessor {


    @Override
    public PropertyModel postProcess(PropertyModel propertyModel, TypeContext typeContext) {
        if (propertyModel.getPropertyItem().getPropertyType() instanceof Class)
        {
            Class clazz = (Class)propertyModel.getPropertyItem().getPropertyType();
            if (clazz.isPrimitive())
            {
                propertyModel.setRequired(true);
            }
        }
        return propertyModel;
    }
}
