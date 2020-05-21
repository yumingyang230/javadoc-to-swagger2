package cn.yumy.doc2swagger.spring.examples.ignoreapi;

import cn.yumy.doc2swagger.core.annotations.IgnoreApi;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ignoreapi")
public class IgnoreApiController {

    @IgnoreApi
    @GetMapping("/ignore")
    public void ignore()
    {
    }

}
