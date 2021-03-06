package cn.yumy.doc2swagger.core.models;

import lombok.Data;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

@Data
public class ReturnModel {

    private Type _returnType;
    /**
     * 描述
     */
    private String _description;
    /**
     * 是否是数组，如果为true，children里包含的是数组Item类型的属性
     */
    private boolean _isArray;
    /**
     * 该参数包含的参数，如参数为对象类型时，包含对象每个key的参数信息
     */
    private List<PropertyModel> _children = new ArrayList<>();
    /**
     * 例子
     */
    private String _example;
    /**
     * 枚举的可选值
     */
    private List<String> _enums;
}
