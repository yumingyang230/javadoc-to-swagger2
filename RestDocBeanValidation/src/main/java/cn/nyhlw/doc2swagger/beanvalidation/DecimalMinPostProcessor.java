package cn.nyhlw.doc2swagger.beanvalidation;

import cn.nyhlw.doc2swagger.core.models.PropertyModel;
import cn.nyhlw.doc2swagger.core.parse.postprocessor.IPropertyPostProcessor;
import cn.nyhlw.doc2swagger.core.parse.utils.TextUtils;
import com.google.auto.service.AutoService;

import javax.validation.constraints.DecimalMin;

/**
 * javax.validation.constraints.DecimalMin
 */
@AutoService(IPropertyPostProcessor.class)
public class DecimalMinPostProcessor extends AbstractBeanValidationPropertyPostProcessor {
    @Override
    public PropertyModel postProcessInternal(PropertyModel propertyModel) {
        DecimalMin minAnno = propertyModel.getPropertyItem().getAnnotation(DecimalMin.class);
        if (minAnno == null) return propertyModel;

        String hint = "";
        if (minAnno.inclusive())
            hint += " (值大于等于" + minAnno.value() + ")";
        else
            hint += " (值大于" + minAnno.value() + ")";
        propertyModel.setDescription(
                TextUtils.combine(propertyModel.getDescription(), hint)
        );
        return propertyModel;
    }
}
