package cn.yumy.doc2swagger.beanvalidation;

import cn.yumy.doc2swagger.core.models.PropertyModel;
import cn.yumy.doc2swagger.core.parse.postprocessor.IPropertyPostProcessor;
import cn.yumy.doc2swagger.core.parse.utils.TextUtils;
import com.google.auto.service.AutoService;

import javax.validation.constraints.AssertTrue;

/**
 * javax.validation.constraints.AssertTrue
 */
@AutoService(IPropertyPostProcessor.class)
public class AssertTruePostProcessor extends AbstractBeanValidationPropertyPostProcessor {
    @Override
    public PropertyModel postProcessInternal(PropertyModel propertyModel) {
        AssertTrue assertTrueAnno = propertyModel.getPropertyItem().getAnnotation(AssertTrue.class);
        if (assertTrueAnno == null) return propertyModel;

        propertyModel.setDescription(TextUtils.combine(propertyModel.getDescription(), " (值只能为true)"));
        return propertyModel;
    }
}
