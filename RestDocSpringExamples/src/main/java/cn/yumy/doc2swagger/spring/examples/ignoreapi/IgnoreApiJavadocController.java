package cn.yumy.doc2swagger.spring.examples.ignoreapi;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ignoreapi/javadoc")
public class IgnoreApiJavadocController {

    /**
     * @ignoreApi
     */
    @GetMapping("/ignore")
    public void ignore()
    {
    }

}
