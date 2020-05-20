package cn.nyhlw.doc2swagger.beanvalidation;

import cn.nyhlw.doc2swagger.core.models.PropertyModel;
import cn.nyhlw.doc2swagger.core.parse.postprocessor.IPropertyPostProcessor;
import cn.nyhlw.doc2swagger.core.parse.utils.TextUtils;
import com.google.auto.service.AutoService;

import javax.validation.constraints.Positive;

/**
 * javax.validation.constraints.Positive
 */
@AutoService(IPropertyPostProcessor.class)
public class PositivePostProcessor extends AbstractBeanValidationPropertyPostProcessor {
    @Override
    public PropertyModel postProcessInternal(PropertyModel propertyModel) {
        Positive positiveAnno = propertyModel.getPropertyItem().getAnnotation(Positive.class);
        if (positiveAnno == null)  return propertyModel;

        propertyModel.setDescription(TextUtils.combine(
                propertyModel.getDescription(),
                " (值只能为正数，不包括0)"
        ));
        return propertyModel;
    }
}
