package cn.yumy.doc2swagger.core.parse;

import cn.yumy.doc2swagger.core.models.RootModel;

public interface IRestDocGenerator {
    String generate(RootModel rootModel);
}
