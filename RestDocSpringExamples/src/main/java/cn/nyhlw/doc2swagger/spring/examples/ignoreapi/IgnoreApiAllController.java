package cn.nyhlw.doc2swagger.spring.examples.ignoreapi;

import cn.nyhlw.doc2swagger.core.annotations.IgnoreApi;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@IgnoreApi
@RestController
@RequestMapping("/ignoreapi/all")
public class IgnoreApiAllController {
    @GetMapping("/ignore")
    public void ignore()
    {
    }
}
