package cn.yumy.doc2swagger.core.parse.postprocessor;

import cn.yumy.doc2swagger.core.models.PropertyModel;
import cn.yumy.doc2swagger.core.models.TypeContext;

public interface IPropertyPostProcessor {
    PropertyModel postProcess(PropertyModel propertyModel, TypeContext typeContext);
}
