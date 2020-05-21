package cn.nyhlw.doc2swagger.spring;

import cn.nyhlw.doc2swagger.core.parse.IMethodFilter;
import cn.nyhlw.doc2swagger.spring.annotation.DubboApi;
import cn.nyhlw.doc2swagger.spring.annotation.DubboUri;
import com.google.auto.service.AutoService;
import lombok.extern.slf4j.Slf4j;
import lombok.var;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.web.bind.annotation.*;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

/**
 * @author yumy
 * @version 1.0.0
 * @date：2020/5/19
 * @desc 过滤方法
 */

@Slf4j
@AutoService(IMethodFilter.class)
public class SpringMethodFilter implements IMethodFilter {

    private static List<Class<? extends Annotation>> classes = Arrays.asList(
            RequestMapping.class,
            GetMapping.class,
            PostMapping.class,
            PutMapping.class,
            DeleteMapping.class,
            PatchMapping.class,
            //增加自定义注解扫描
//            DubboApi.class,
            DubboUri.class
    );

/*
    @Override
    public boolean isSupport(Method method) {
        if (method.isSynthetic() || method.isBridge())
            return false;
        // 如果方法和类上都没有ResponseBody，返回false
        if (!AnnotatedElementUtils.hasAnnotation(method, ResponseBody.class) &&
            !AnnotatedElementUtils.hasAnnotation(method.getDeclaringClass(), ResponseBody.class))
        {
                return false;
        }
        var annotations = method.getAnnotations();
        for (var annotation : annotations) {
            var annotationType = annotation.annotationType();

            if (_classes.contains(annotationType))
                return true;
        }
        return false;
    }
*/

    @Override
    public boolean isSupport(Method method) {
        if (method.isSynthetic() || method.isBridge()) {
            return false;
        }
        // 如果方法和类上都没有ResponseBody，返回false
        // todo 过滤注解增加 DubboUri
        if (!AnnotatedElementUtils.hasAnnotation(method, DubboUri.class) &&
                !AnnotatedElementUtils.hasAnnotation(method, ResponseBody.class) &&
                !AnnotatedElementUtils.hasAnnotation(method.getDeclaringClass(), ResponseBody.class)) {
            return false;
        }
        var annotations = method.getAnnotations();
        for (var annotation : annotations) {
            var annotationType = annotation.annotationType();

            if (classes.contains(annotationType)) {
                return true;
            }
        }
        return false;
    }
}
