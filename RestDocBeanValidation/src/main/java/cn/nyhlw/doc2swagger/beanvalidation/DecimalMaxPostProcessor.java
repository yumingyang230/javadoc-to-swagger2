package cn.nyhlw.doc2swagger.beanvalidation;

import cn.nyhlw.doc2swagger.core.models.PropertyModel;
import cn.nyhlw.doc2swagger.core.parse.postprocessor.IPropertyPostProcessor;
import cn.nyhlw.doc2swagger.core.parse.utils.TextUtils;
import com.google.auto.service.AutoService;

import javax.validation.constraints.DecimalMax;

/**
 * javax.validation.constraints.DecimalMax
 */
@AutoService(IPropertyPostProcessor.class)
public class DecimalMaxPostProcessor extends AbstractBeanValidationPropertyPostProcessor {
    @Override
    public PropertyModel postProcessInternal(PropertyModel propertyModel) {
        DecimalMax maxAnno = propertyModel.getPropertyItem().getAnnotation(DecimalMax.class);
        if (maxAnno == null) return propertyModel;

        String hint = "";
        if (maxAnno.inclusive())
            hint += " (值小于等于" + maxAnno.value() + ")";
        else
            hint += " (值小于" + maxAnno.value() + ")";
        propertyModel.setDescription(
                TextUtils.combine(propertyModel.getDescription(), hint)
        );
        return propertyModel;
    }
}
