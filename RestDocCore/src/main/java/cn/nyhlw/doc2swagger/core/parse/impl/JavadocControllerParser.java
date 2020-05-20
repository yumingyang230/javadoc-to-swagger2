package cn.nyhlw.doc2swagger.core.parse.impl;

import cn.nyhlw.doc2swagger.core.config.ExtOrder;
import cn.nyhlw.doc2swagger.core.parse.utils.FormatUtils;
import cn.nyhlw.doc2swagger.core.models.ControllerModel;
import com.github.therapi.runtimejavadoc.ClassJavadoc;
import cn.nyhlw.doc2swagger.core.parse.IControllerParser;
import com.google.auto.service.AutoService;

@AutoService(IControllerParser.class)
@ExtOrder(Integer.MIN_VALUE)
public class JavadocControllerParser implements IControllerParser {

    @Override
    public void parse(Class clazz, ClassJavadoc classDoc, ControllerModel controllerModel) {
        controllerModel.setControllerClass(clazz);
        if (classDoc != null && classDoc.isPresent()) {
            controllerModel.setDescription(FormatUtils.format(classDoc.getComment()));
        }
    }
}
