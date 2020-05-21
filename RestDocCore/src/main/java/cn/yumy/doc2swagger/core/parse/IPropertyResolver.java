package cn.yumy.doc2swagger.core.parse;

import cn.yumy.doc2swagger.core.models.PropertyItem;

import java.lang.reflect.Type;
import java.util.List;

/**
 * 从type中解析出属性列表
 */
public interface IPropertyResolver {
    /**
     * 解析类型包含的属性
     */
    List<PropertyItem> resolve(Type type);
}
