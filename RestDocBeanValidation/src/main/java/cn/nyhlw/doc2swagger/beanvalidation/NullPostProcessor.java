package cn.nyhlw.doc2swagger.beanvalidation;

import cn.nyhlw.doc2swagger.core.models.PropertyModel;
import cn.nyhlw.doc2swagger.core.parse.postprocessor.IPropertyPostProcessor;
import com.google.auto.service.AutoService;

import javax.validation.constraints.Null;

/**
 * javax.validation.constraints.Null
 */
@AutoService(IPropertyPostProcessor.class)
public class NullPostProcessor extends AbstractBeanValidationPropertyPostProcessor {
    @Override
    public PropertyModel postProcessInternal(PropertyModel propertyModel) {
        Null nullAnno = propertyModel.getPropertyItem().getAnnotation(Null.class);
        if (nullAnno == null)  return propertyModel;

        propertyModel.setRequired(false);

        return propertyModel;
    }
}
