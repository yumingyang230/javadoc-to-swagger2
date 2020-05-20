package cn.nyhlw.doc2swagger.beanvalidation;

import cn.nyhlw.doc2swagger.core.models.PropertyModel;
import cn.nyhlw.doc2swagger.core.parse.postprocessor.IPropertyPostProcessor;
import cn.nyhlw.doc2swagger.core.parse.utils.TextUtils;
import com.google.auto.service.AutoService;

import javax.validation.constraints.Max;

/**
 * javax.validation.constraints.Max
 */
@AutoService(IPropertyPostProcessor.class)
public class MaxPostProcessor extends AbstractBeanValidationPropertyPostProcessor {
    @Override
    public PropertyModel postProcessInternal(PropertyModel propertyModel) {
        Max maxAnno = propertyModel.getPropertyItem().getAnnotation(Max.class);
        if (maxAnno == null) return propertyModel;

        propertyModel.setDescription(
                TextUtils.combine(propertyModel.getDescription(),
                        String.format(" (值小于等于%s)", maxAnno.value())
                )
        );
        return propertyModel;
    }
}
