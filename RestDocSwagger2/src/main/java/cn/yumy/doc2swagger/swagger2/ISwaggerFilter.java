package cn.yumy.doc2swagger.swagger2;

import io.swagger.models.Swagger;

public interface ISwaggerFilter {
    Swagger handle(Swagger swagger);
}
