package cn.nyhlw.doc2swagger.core.parse;

import cn.nyhlw.doc2swagger.core.models.PropertyItem;
import cn.nyhlw.doc2swagger.core.models.PropertyModel;

/**
 * 解析属性
 */
public interface IPropertyParser {
    /**
     * 如果返回null，表示忽略该属性
     */
    PropertyModel parse(PropertyItem item);
}
