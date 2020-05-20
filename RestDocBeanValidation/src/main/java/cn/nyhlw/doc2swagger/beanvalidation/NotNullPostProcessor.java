package cn.nyhlw.doc2swagger.beanvalidation;

import cn.nyhlw.doc2swagger.core.models.PropertyModel;
import cn.nyhlw.doc2swagger.core.parse.postprocessor.IPropertyPostProcessor;
import com.google.auto.service.AutoService;

import javax.validation.constraints.NotNull;

/**
 * javax.validation.constraints.NotNull
 */
@AutoService(IPropertyPostProcessor.class)
public class NotNullPostProcessor extends AbstractBeanValidationPropertyPostProcessor {
    @Override
    public PropertyModel postProcessInternal(PropertyModel propertyModel) {
        NotNull notNullAnno = propertyModel.getPropertyItem().getAnnotation(NotNull.class);
        if (notNullAnno == null)  return propertyModel;

        propertyModel.setRequired(true);

        return propertyModel;
    }
}
