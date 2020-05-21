package cn.yumy.doc2swagger.spring.parameter.parser;

import cn.yumy.doc2swagger.core.config.ExtOrder;
import cn.yumy.doc2swagger.core.models.ParameterModel;
import cn.yumy.doc2swagger.core.parse.IMethodParameterParser;
import cn.yumy.doc2swagger.core.parse.impl.AbstractMethodParameterParser;
import com.google.auto.service.AutoService;

import java.lang.reflect.Parameter;
import java.lang.reflect.Type;

@AutoService(IMethodParameterParser.class)
@ExtOrder(600)
public class SpringPrimitiveParameterParser extends AbstractMethodParameterParser {

    @Override
    protected ParameterModel.ParameterLocation getParameterLocation(Parameter parameter, Type actualParamType) {
        return ParameterModel.ParameterLocation.QUERY;
    }

    @Override
    protected boolean isRequired(Parameter parameter, Type actualParamType) {
        return false;
    }

    @Override
    public boolean isSupport(Parameter parameter) {
        return true;
    }
}
