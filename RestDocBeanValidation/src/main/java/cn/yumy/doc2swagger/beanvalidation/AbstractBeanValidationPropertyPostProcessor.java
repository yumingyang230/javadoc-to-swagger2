package cn.yumy.doc2swagger.beanvalidation;

import cn.yumy.doc2swagger.core.models.PropertyItem;
import cn.yumy.doc2swagger.core.models.PropertyModel;
import cn.yumy.doc2swagger.core.models.TypeContext;
import cn.yumy.doc2swagger.core.parse.postprocessor.IPropertyPostProcessor;

import javax.validation.Valid;
import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedParameterizedType;
import java.lang.reflect.AnnotatedType;
import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 * 基于注解的属性
 */
public abstract class AbstractBeanValidationPropertyPostProcessor<T extends Annotation> implements IPropertyPostProcessor {
    @Override
    public PropertyModel postProcess(PropertyModel propertyModel, TypeContext typeContext) {
        if (!cascadeValid(propertyModel, typeContext)) return propertyModel;

        return postProcessInternal(propertyModel);
    }

    private boolean cascadeValid(PropertyModel propertyModel, TypeContext typeContext) {
        PropertyItem parent = propertyModel.getParentPropertyItem();
        if (parent == null) return true;

        Valid validAnno = parent.getAnnotation(Valid.class);
        if (validAnno != null)
            return true;

        AnnotatedType annotatedType = null;
        if (parent.getField() != null) {
            annotatedType = parent.getField().getAnnotatedType();
        } else if (parent.getGetMethod() != null) {
            annotatedType = parent.getGetMethod().getAnnotatedReturnType();
        } else if (parent.getSetMethod() != null) {
            annotatedType = parent.getGetMethod().getAnnotatedParameterTypes()[0];
        }
        if (annotatedType != null) {
            if (annotatedType instanceof AnnotatedParameterizedType) {
                AnnotatedParameterizedType annotatedParameterizedType = (AnnotatedParameterizedType) annotatedType;
                if (annotatedParameterizedType.getType() instanceof ParameterizedType) {
                    Class clazz = (Class) ((ParameterizedType) annotatedParameterizedType.getType()).getRawType();
                    if (List.class.isAssignableFrom(clazz)) {
                        if (annotatedParameterizedType.getAnnotatedActualTypeArguments()[0].getAnnotation(Valid.class) != null)
                            return true;
                    }
                }
            }
        }
        return false;
    }

    protected abstract PropertyModel postProcessInternal(PropertyModel propertyModel);
}
