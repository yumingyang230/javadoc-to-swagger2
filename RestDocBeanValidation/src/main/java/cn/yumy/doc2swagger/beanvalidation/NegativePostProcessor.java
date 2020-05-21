package cn.yumy.doc2swagger.beanvalidation;

import cn.yumy.doc2swagger.core.models.PropertyModel;
import cn.yumy.doc2swagger.core.parse.postprocessor.IPropertyPostProcessor;
import cn.yumy.doc2swagger.core.parse.utils.TextUtils;
import com.google.auto.service.AutoService;

import javax.validation.constraints.Negative;

/**
 * javax.validation.constraints.Negative
 */
@AutoService(IPropertyPostProcessor.class)
public class NegativePostProcessor extends AbstractBeanValidationPropertyPostProcessor {
    @Override
    public PropertyModel postProcessInternal(PropertyModel propertyModel) {
        Negative negativeAnno = propertyModel.getPropertyItem().getAnnotation(Negative.class);
        if (negativeAnno == null) return propertyModel;


        propertyModel.setDescription(TextUtils.combine(
                propertyModel.getDescription(),
                " (值只能为负数，不包括0)"
        ));
        return propertyModel;
    }
}
