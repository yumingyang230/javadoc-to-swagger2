package cn.yumy.doc2swagger.core.parse.impl;

import cn.yumy.doc2swagger.core.models.ParameterModel;
import cn.yumy.doc2swagger.core.models.TypeContext;
import cn.yumy.doc2swagger.core.config.AbstractRestDocParseConfigAware;
import cn.yumy.doc2swagger.core.parse.utils.ParamUtils;
import com.github.therapi.runtimejavadoc.ParamJavadoc;
import cn.yumy.doc2swagger.core.parse.IMethodParameterParser;
import cn.yumy.doc2swagger.core.parse.utils.FormatUtils;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Optional;

public abstract class AbstractMethodParameterParser extends AbstractRestDocParseConfigAware implements IMethodParameterParser {

    @Override
    public ParameterModel parse(Parameter parameter, ParamJavadoc paramJavadoc, ParameterModel parameterModel) {
        parameterModel.setName(getParameterName(parameter));

        if (paramJavadoc != null)
        {
            parameterModel.setDescription(FormatUtils.format(paramJavadoc.getComment()));
        }
        // 参数是Optional类型
        Type actualParamType = parameter.getParameterizedType();
        if (parameter.getType() == Optional.class)
        {
            if (parameter.getParameterizedType() instanceof ParameterizedType)
            {
                actualParamType = ((ParameterizedType)parameter.getParameterizedType()).getActualTypeArguments()[0];
            }
            else
            {
                throw new RuntimeException("why?");
            }
        }
        parameterModel.setParameterType(actualParamType);

        parameterModel.setLocation(getParameterLocation(parameter, actualParamType));
        parameterModel = parseInternal(parameter, actualParamType, parameterModel);
        parameterModel.setRequired(isRequired(parameter, actualParamType));

        if (actualParamType != parameter.getParameterizedType())
            parameterModel.setRequired(false);

        return parameterModel;
    }

    protected String getParameterName(Parameter parameter) {
        return ParamUtils.discoverParameterName(parameter);
    }

    protected abstract ParameterModel.ParameterLocation getParameterLocation(Parameter parameter, Type actualParamType);

    protected abstract boolean isRequired(Parameter parameter, Type actualParamType);

    protected ParameterModel parseInternal(Parameter parameter, Type actualParamType, ParameterModel parameterModel)
    {
        boolean isArray = _config.getTypeInspector().isCollection(actualParamType);
        parameterModel.setArray(isArray);

        if (!isArray) {
            parameterModel.setChildren(_config.getTypeParser().parse(new TypeContext(actualParamType, parameter, (Method) parameter.getDeclaringExecutable())));
        }
        else
        {
            parameterModel.setChildren(_config.getTypeParser()
                    .parse(new TypeContext(_config.getTypeInspector().getCollectionComponentType(actualParamType),
                            parameter, (Method) parameter.getDeclaringExecutable())));
        }
        return parameterModel;
    }
}
