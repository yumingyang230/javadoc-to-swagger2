package cn.yumy.doc2swagger.spring.examples.ext;

import cn.yumy.doc2swagger.swagger2.ISwaggerFilter;
import com.google.auto.service.AutoService;
import io.swagger.models.Swagger;

@AutoService(ISwaggerFilter.class)
public class TestSwaggerFilter implements ISwaggerFilter {
    @Override
    public Swagger handle(Swagger swagger) {
        System.out.println("handle swagger");

//        swagger.setHost("http://localhost:8084?info=add_by_extension");

        return swagger;
    }
}
