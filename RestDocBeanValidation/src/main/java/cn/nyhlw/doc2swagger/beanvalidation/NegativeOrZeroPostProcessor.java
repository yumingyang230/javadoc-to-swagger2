package cn.nyhlw.doc2swagger.beanvalidation;

import cn.nyhlw.doc2swagger.core.models.PropertyModel;
import cn.nyhlw.doc2swagger.core.parse.postprocessor.IPropertyPostProcessor;
import cn.nyhlw.doc2swagger.core.parse.utils.TextUtils;
import com.google.auto.service.AutoService;

import javax.validation.constraints.NegativeOrZero;

/**
 * javax.validation.constraints.NegativeOrZero
 */
@AutoService(IPropertyPostProcessor.class)
public class NegativeOrZeroPostProcessor extends AbstractBeanValidationPropertyPostProcessor {
    @Override
    public PropertyModel postProcessInternal(PropertyModel propertyModel) {
        NegativeOrZero negativeAnno = propertyModel.getPropertyItem().getAnnotation(NegativeOrZero.class);
        if (negativeAnno == null) return propertyModel;

        propertyModel.setDescription(TextUtils.combine(
                propertyModel.getDescription(),
                " (值只能为负数或0)"
        ));
        return propertyModel;
    }
}
