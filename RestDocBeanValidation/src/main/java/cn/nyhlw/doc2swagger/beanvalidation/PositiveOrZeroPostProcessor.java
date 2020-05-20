package cn.nyhlw.doc2swagger.beanvalidation;

import cn.nyhlw.doc2swagger.core.models.PropertyModel;
import cn.nyhlw.doc2swagger.core.parse.postprocessor.IPropertyPostProcessor;
import cn.nyhlw.doc2swagger.core.parse.utils.TextUtils;
import com.google.auto.service.AutoService;

import javax.validation.constraints.PositiveOrZero;

/**
 * javax.validation.constraints.PositiveOrZero
 */
@AutoService(IPropertyPostProcessor.class)
public class PositiveOrZeroPostProcessor extends AbstractBeanValidationPropertyPostProcessor {
    @Override
    public PropertyModel postProcessInternal(PropertyModel propertyModel) {
        PositiveOrZero positiveOrZeroAnno = propertyModel.getPropertyItem().getAnnotation(PositiveOrZero.class);
        if (positiveOrZeroAnno == null)  return propertyModel;

        propertyModel.setDescription(TextUtils.combine(
                propertyModel.getDescription(),
                " (值只能为正数，包括0)"
        ));
        return propertyModel;
    }
}
