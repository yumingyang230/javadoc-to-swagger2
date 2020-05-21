package cn.yumy.doc2swagger.core.parse;

import cn.yumy.doc2swagger.core.models.PropertyModel;
import cn.yumy.doc2swagger.core.models.TypeContext;

import java.util.List;

/**
 * 解析Type为 PropertyModel 列表
 */
public interface ITypeParser {
    List<PropertyModel> parse(TypeContext typeContext);
}
