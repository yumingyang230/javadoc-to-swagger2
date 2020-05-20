package cn.nyhlw.doc2swagger.spring.examples.ignoreapi;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ignoreApi
 */
@RestController
@RequestMapping("/ignoreapi/javadoc/all")
public class IgnoreApiJavadocAllController {
    @GetMapping("/ignore")
    public void ignore()
    {
    }
}
