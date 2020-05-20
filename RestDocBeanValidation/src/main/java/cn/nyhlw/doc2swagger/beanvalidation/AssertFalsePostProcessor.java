package cn.nyhlw.doc2swagger.beanvalidation;

import cn.nyhlw.doc2swagger.core.models.PropertyModel;
import cn.nyhlw.doc2swagger.core.parse.postprocessor.IPropertyPostProcessor;
import cn.nyhlw.doc2swagger.core.parse.utils.TextUtils;
import com.google.auto.service.AutoService;

import javax.validation.constraints.AssertFalse;

/**
 * javax.validation.constraints.AssertFalse
 */
@AutoService(IPropertyPostProcessor.class)
public class AssertFalsePostProcessor extends AbstractBeanValidationPropertyPostProcessor {
    @Override
    public PropertyModel postProcessInternal(PropertyModel propertyModel) {
        AssertFalse assertFalseAnno = propertyModel.getPropertyItem().getAnnotation(AssertFalse.class);
        if (assertFalseAnno == null) return propertyModel;

        propertyModel.setDescription(TextUtils.combine(propertyModel.getDescription(), " (值只能为false)"));

        return propertyModel;
    }
}
