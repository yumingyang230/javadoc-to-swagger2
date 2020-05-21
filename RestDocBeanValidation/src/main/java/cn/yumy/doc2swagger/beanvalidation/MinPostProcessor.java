package cn.yumy.doc2swagger.beanvalidation;

import cn.yumy.doc2swagger.core.models.PropertyModel;
import cn.yumy.doc2swagger.core.parse.postprocessor.IPropertyPostProcessor;
import cn.yumy.doc2swagger.core.parse.utils.TextUtils;
import com.google.auto.service.AutoService;

import javax.validation.constraints.Min;

/**
 * javax.validation.constraints.Min
 */
@AutoService(IPropertyPostProcessor.class)
public class MinPostProcessor extends AbstractBeanValidationPropertyPostProcessor {
    @Override
    public PropertyModel postProcessInternal(PropertyModel propertyModel) {
        Min minAnno = propertyModel.getPropertyItem().getAnnotation(Min.class);
        if (minAnno == null) return propertyModel;

        propertyModel.setDescription(
                TextUtils.combine(propertyModel.getDescription(),
                        String.format(" (值大于等于%s)", minAnno.value())
                )
        );
        return propertyModel;
    }
}
