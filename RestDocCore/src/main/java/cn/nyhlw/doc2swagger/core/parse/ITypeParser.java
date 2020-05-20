package cn.nyhlw.doc2swagger.core.parse;

import cn.nyhlw.doc2swagger.core.models.PropertyModel;
import cn.nyhlw.doc2swagger.core.models.TypeContext;

import java.util.List;

/**
 * 解析Type为 PropertyModel 列表
 */
public interface ITypeParser {
    List<PropertyModel> parse(TypeContext typeContext);
}
