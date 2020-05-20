package cn.nyhlw.doc2swagger.core.parse.postprocessor;

import cn.nyhlw.doc2swagger.core.models.PropertyModel;
import cn.nyhlw.doc2swagger.core.models.TypeContext;

public interface IPropertyPostProcessor {
    PropertyModel postProcess(PropertyModel propertyModel, TypeContext typeContext);
}
