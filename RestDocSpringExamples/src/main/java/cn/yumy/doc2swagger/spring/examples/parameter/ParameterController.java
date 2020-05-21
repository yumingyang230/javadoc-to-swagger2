package cn.yumy.doc2swagger.spring.examples.parameter;

import org.springframework.web.bind.annotation.*;

/**
 * 参数
 */
@RestController
@RequestMapping("/parameter")
public class ParameterController {
    /**
     * 查询字符串
     */
    @GetMapping("/queryString")
    public void queryString(@RequestParam int param1, @RequestParam(required = false) String param2)
    {
    }

    /**
     * 查询参数数组
     * @param array 字符串参数数组
     * @param parameterAS 参数A数组
     */
    @GetMapping("/queryString/array")
    public void queryStringArray(String[] array, cn.yumy.doc2swagger.spring.examples.parameter.ParameterA[] parameterAS)
    {
    }

    /**
     * 查询字符串 复杂参数
     */
    @GetMapping("/queryString/complex")
    public void queryStringComplex(cn.yumy.doc2swagger.spring.examples.parameter.ParameterA parameterA) // todo
    {
    }

    /**
     * 表单post
     */
    @PostMapping("/form/complex")
    public void formComplex(cn.yumy.doc2swagger.spring.examples.parameter.ParameterA parameterA)
    {
    }

    /**
     * body 中的简单数组
     */
    @PostMapping("/body/array/simple")
    public void simpleArray(@RequestBody String[] arr)
    {
    }

    /**
     * body 中的复杂数组
     */
    @PostMapping("/body/array/complex")
    public void complexArray(@RequestBody cn.yumy.doc2swagger.spring.examples.parameter.ParameterA[] arr)
    {
    }

    /**
     * body 里的复杂对象
     */
    @PostMapping("/body/complex")
    public void complex(@RequestBody cn.yumy.doc2swagger.spring.examples.parameter.ParameterA obj)
    {
    }

    /**
     * path 变量
     * @param parameterA  参数A
     * @param parameterB  参数B
     */
    @PostMapping("/path/{parameterA}/{parameterB}")
    public void path(@PathVariable String parameterA, @PathVariable String parameterB)
    {
    }

    /**
     * 泛型复杂参数
     */
    @PostMapping("/body/generic/complex")
    public void bodyGenericComplex(@RequestBody cn.yumy.doc2swagger.spring.examples.parameter.GenericParameter<cn.yumy.doc2swagger.spring.examples.parameter.ParameterA> parameterAGenericParameter)
    {
    }

    /**
     * 泛型简单参数
     */
    @PostMapping("/body/generic/simple")
    public void bodyGenericSimple(@RequestBody cn.yumy.doc2swagger.spring.examples.parameter.GenericParameter<Integer> parameter)
    {
    }

    /**
     * 双重泛型参数
     */
    @PostMapping("/body/generic/multi")
    public void bodyGenericMulti(@RequestBody cn.yumy.doc2swagger.spring.examples.parameter.MultipartGenericParameter<Integer, cn.yumy.doc2swagger.spring.examples.parameter.ParameterA> parameter)
    {
    }

}
