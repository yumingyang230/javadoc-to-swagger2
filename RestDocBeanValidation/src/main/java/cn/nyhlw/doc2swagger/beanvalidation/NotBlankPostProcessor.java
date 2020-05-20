package cn.nyhlw.doc2swagger.beanvalidation;

import cn.nyhlw.doc2swagger.core.models.PropertyModel;
import cn.nyhlw.doc2swagger.core.parse.postprocessor.IPropertyPostProcessor;
import cn.nyhlw.doc2swagger.core.parse.utils.TextUtils;
import com.google.auto.service.AutoService;

import javax.validation.constraints.NotBlank;

/**
 * javax.validation.constraints.NotBlank
 */
@AutoService(IPropertyPostProcessor.class)
public class NotBlankPostProcessor extends AbstractBeanValidationPropertyPostProcessor {
    @Override
    public PropertyModel postProcessInternal(PropertyModel propertyModel) {
        NotBlank notBlankAnno = propertyModel.getPropertyItem().getAnnotation(NotBlank.class);
        if (notBlankAnno == null) return propertyModel;


        propertyModel.setRequired(true);
        propertyModel.setDescription(TextUtils.combine(propertyModel.getDescription(), " (值不能仅包含空白字符)"));

        return propertyModel;
    }
}
