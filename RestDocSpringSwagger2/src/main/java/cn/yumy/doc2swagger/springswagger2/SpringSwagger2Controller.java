package cn.yumy.doc2swagger.springswagger2;

import cn.yumy.doc2swagger.core.parse.IRestDocParser;
import cn.yumy.doc2swagger.swagger.common.SwaggerUIConfiguration;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SpringSwagger2Controller {

    private final SwaggerUIConfiguration _uiConfiguration;
    private IRestDocParser _docParser;
    private String _docCache;
    private ObjectMapper _objectMapper = new ObjectMapper();

    public SpringSwagger2Controller(@Qualifier("swagger2") IRestDocParser docParser, SwaggerUIConfiguration swaggerUIConfiguration)
    {
        _docParser = docParser;
        _uiConfiguration = swaggerUIConfiguration;
    }

    @GetMapping(value = "/swagger2.json")
    public String swaggerJson()
    {
//        if (_docCache != null)
//            return _docCache;
        _docCache = _docParser.parse();
        return _docCache;
    }

    @GetMapping("/swagger2/swaggerUIConfiguration")
    public String swaggerUIConfiguration() throws JsonProcessingException {
        return _objectMapper.writeValueAsString(_uiConfiguration);
    }
}
