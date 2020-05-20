package cn.nyhlw.doc2swagger.beanvalidation;

import cn.nyhlw.doc2swagger.core.models.PropertyModel;
import cn.nyhlw.doc2swagger.core.parse.postprocessor.IPropertyPostProcessor;
import cn.nyhlw.doc2swagger.core.parse.utils.TextUtils;
import com.google.auto.service.AutoService;

import javax.validation.constraints.Size;

/**
 * javax.validation.constraints.Size
 */
@AutoService(IPropertyPostProcessor.class)
public class SizePostProcessor extends AbstractBeanValidationPropertyPostProcessor {
    @Override
    public PropertyModel postProcessInternal(PropertyModel propertyModel) {
        Size sizeAnno = propertyModel.getPropertyItem().getAnnotation(Size.class);
        if (sizeAnno == null)  return propertyModel;

        propertyModel.setDescription(TextUtils.combine(
                propertyModel.getDescription(),
                String.format(" (元素的数量在%s和%s之间，包括%s和%s)", sizeAnno.min(), sizeAnno.max(), sizeAnno.min(), sizeAnno.max())
        ));
        return propertyModel;
    }
}
