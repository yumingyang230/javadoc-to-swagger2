package cn.yumy.doc2swagger.core.parse;

import java.util.List;

/**
 * 获取可处理的类
 */
public interface IControllerResolver {
    List<Class> getClasses();
}
