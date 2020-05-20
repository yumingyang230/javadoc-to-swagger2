package cn.nyhlw.doc2swagger.core.parse;

import cn.nyhlw.doc2swagger.core.models.RootModel;

public interface IRestDocGenerator {
    String generate(RootModel rootModel);
}
