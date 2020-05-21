package cn.yumy.doc2swagger.core.parse;

import cn.yumy.doc2swagger.core.models.ControllerModel;
import com.github.therapi.runtimejavadoc.ClassJavadoc;

/**
 * 解析controller
 */
public interface IControllerParser {
    void parse(Class clazz, ClassJavadoc classJavadoc, ControllerModel controllerModel);
}
