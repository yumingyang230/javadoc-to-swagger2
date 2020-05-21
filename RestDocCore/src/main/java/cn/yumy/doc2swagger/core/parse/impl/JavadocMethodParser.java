package cn.yumy.doc2swagger.core.parse.impl;

import cn.yumy.doc2swagger.core.config.ExtOrder;
import cn.yumy.doc2swagger.core.parse.utils.FormatUtils;
import cn.yumy.doc2swagger.core.models.PathModel;
import com.github.therapi.runtimejavadoc.MethodJavadoc;
import cn.yumy.doc2swagger.core.parse.IMethodParser;
import com.google.auto.service.AutoService;

import java.lang.reflect.Method;

@AutoService(IMethodParser.class)
@ExtOrder(Integer.MIN_VALUE)
public class JavadocMethodParser implements IMethodParser {
    @Override
    public PathModel parse(Method method, MethodJavadoc methodJavadoc, PathModel pathModel) {
        if (methodJavadoc != null)
        {
            pathModel.setDescription(FormatUtils.format(methodJavadoc.getComment()));
        }
        return pathModel;
    }
}
