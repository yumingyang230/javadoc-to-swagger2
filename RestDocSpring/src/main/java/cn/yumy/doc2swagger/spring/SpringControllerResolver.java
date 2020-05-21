package cn.yumy.doc2swagger.spring;

import cn.yumy.doc2swagger.core.parse.IControllerResolver;
import cn.yumy.doc2swagger.spring.annotation.DubboApi;
import lombok.extern.slf4j.Slf4j;
import lombok.var;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.stereotype.Controller;

import java.util.*;
/**
* @date：2020/5/19
* @author yumy
* @version 1.0.0
* @desc 扫描包下边标注有 DubboApi 注解的类
*/
@Slf4j
public class SpringControllerResolver implements IControllerResolver {
    private final List<String> packages;

    public SpringControllerResolver(List<String> packages) {
        this.packages = packages;
    }

    @Override
    public List<Class> getClasses() {
        List<String> packages = this.packages;

        var scanner = new ClassPathScanningCandidateComponentProvider(false);
        scanner.addIncludeFilter(new AnnotationTypeFilter(Controller.class));
        scanner.addIncludeFilter(new AnnotationTypeFilter(DubboApi.class));

        var classes = new ArrayList<Class>();
        if (packages == null) {
            packages = Arrays.asList("cn", "com");
        }
        for (var packageName : packages) {
            var beans = scanner.findCandidateComponents(packageName);
            for (var bean : beans) {
                try {
                    var className = bean.getBeanClassName();
                    Class clazz = Class.forName(className);

                    classes.add(clazz);
                } catch (ClassNotFoundException e) {
                    log.warn("not found class:" + bean.getBeanClassName(), e);
                }
            }
        }
        return classes;
    }
}
