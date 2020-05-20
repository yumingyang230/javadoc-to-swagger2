package cn.nyhlw.doc2swagger.spring.examples.parameter;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * 类型ParameterA类
 */
@Data
public class ParameterA
{
    /**
     * 参数id
     */
    @NotEmpty
    private int _id;
    /**
     * 参数name
     */
    @NotNull
    private String _name;
    /**
     * 参数parameterB
     */
    private cn.nyhlw.doc2swagger.spring.examples.parameter.ParameterB _parameterB;
    /**
     * 参数ParameterBArray数组
     */
    private cn.nyhlw.doc2swagger.spring.examples.parameter.ParameterB[] _parameterBArray;
    private boolean _hasPassword;
}
