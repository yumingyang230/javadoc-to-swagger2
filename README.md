## 介绍

该项目用于在运行时使用javadoc生成swagger文档，并使用swagger-ui进行显示。

RestDocBeanValidation----Validation校验注解的解析说明
RestDocCore----核心的一些配置对象属性
           ----核心doc转换工具接口
ResDocJackson----Jackson相关的操作
RestDocSpring----集成spring的过滤器
            -----SpringControllerResolver   ：扫描自定义路径或者有某些注解的controller
            -----SpringMethodFilter         ：扫描controller中有某些注解的method
RestDocSpringExamples--------使用示例
RestDocSpringSwagger2--------有关swagger2入口的配置
RestDocSwagger2-------主要加载doc解析类
![示例](./images/example_summary.png?)


## 使用

第一步, 为SpringBoot项目中配置依赖, 配置RestDocConfig

Maven项目增加依赖：

```
<dependency>
     <groupId>cn.yumy.doc2swagger</groupId>
     <artifactId>RestDocSpringSwagger2</artifactId>
     <version>{实际发布的version}</version>
</dependency>
<dependency>
    <groupId>com.github.therapi</groupId>
    <artifactId>therapi-runtime-javadoc-scribe</artifactId>
    <version>0.9.0</version>
    <scope>provided</scope>
</dependency>
```

新建配置文件，如下：

```java 
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    RestDocConfig _swaggerConfig()
    {
            return RestDocConfig.builder()
                    .apiTitle("rest doc title")
                    .apiDescription("rest doc desc")
                    .apiVersion("api version")
                    .packages(Arrays.asList("cn.yumy.doc2swagger.spring.examples"))
                    .build();
    }
}
```

主要需要修改包名为自己的应用包名。详细的配置参考 [配置参考](##配置参考)

第二步，启用Annotation Processors 

- **IntelliJ IDEA**: File > Settings > Preferences > Build, Execution, Deployment > Compiler > Annotation Processors > 勾选"Enable annotation processing".
![编译设置](./images/compile-setting.png?)

第三步、应用到项目

直接使用常用的注解就可以：
    @RestController
    @Controller
    @RequestMapping
        @GetMapping
        @PostMapping
    
也可以使用@DubboApi、@DubboUri注解配合（没有controller只有service接口的方式推荐使用此注解）
````
/**
 * 自定义注解测试
 */
@DubboApi(path = "/dubboController")
@Validated
public class DubboController {

    /**
    * 获取用户
    * @param user 用户对象信息
    * @return cn.yumy.doc2swagger.spring.examples.dubbo.DubboController.User
    * @throws
    */
    @DubboUri(path = "/getUsr", method = RequestMethod.POST)
    public User getUsr(User user) {
        User result = new User();
        result.setName("王德发");
        result.setAge(18);
        return result;
    }
````

启动应用后，打开 http://host/swagger-ui/index.html 浏览

具体可参考 [RestDocSpringExamples](https://github.com/yumingyang230/javadoc-to-swagger2/tree/master/RestDocSpringExamples)。

## BeanValidation支持

如果需要BeanValidation的支持，需要增加以下依赖：
```
<dependency>
    <groupId>cn.yumy.doc2swagger</groupId>
    <artifactId>RestDocBeanValidation</artifactId>
    <version>{发布的版本号}</version>
</dependency>
```

支持BeanValidation的注解：

- NotNull
- AssertFalse
- AssertTrue
- DecimalMax
- DecimalMin
- Email （不支持正则表达式）
- Max
- Min
- NegativeOrZero
- Negative
- NotBlank
- NotEmpty
- NotNull
- Null
- PositiveOrZero
- Positive

## Jackson支持

如果需要Jackson的支持，增加以下依赖：
```
<dependency>
    <groupId>cn.yumy.doc2swagger</groupId>
    <artifactId>RestDocJackson</artifactId>
    <version>{发布的版本号}</version>
</dependency>
```

支持的Jackson注解：

- JsonGetter
- JsonSetter
- JsonIgnore
- JsonIgnoreType
- JsonProperty (不支持对枚举的操作)
- JsonIgnoreProperties (仅支持value属性)


## 配置参考

```java 
@Bean
RestDocConfig _swaggerConfig()
{
        return RestDocConfig.builder()
                //配置文档标题
                .apiTitle("rest doc title")
                //配置文档描述
                .apiDescription("rest doc desc")
                //配置文档版本
                .apiVersion("api version")
                //是否将类的javadoc解析为swagger显示名称
                .resolveJavaDocAsTypeName(true)
                //是否隐藏没有方法的Controller
                .hideEmptyController(true)
                //配置扫描的包
                .packages(Arrays.asList("cn.yumy.doc2swagger.spring.examples"))
                //启用httpBasic认证
                .httpBasicAuth(new RestDocConfig.HttpBasicAuth("restdoc","restdoc"))
                //配置接口地址
                .servers(Arrays.asList(RestDocConfig.Server.builder().description("url desc").url("localhost:8080").build()))
                //配置field前缀
                .fieldPrefix("_")
                .build();
}
```
## 扩展配置参考
```java 
@Bean
RestDocConfigSwagger2Ext restDocConfigSwagger2Ext()
{
    return RestDocConfigSwagger2Ext.builder()
            .swaggerFilters(Arrays.asList(new TestSwaggerFilter()))
            .build();
}


@AutoService(ISwaggerFilter.class)
public class TestSwaggerFilter implements ISwaggerFilter {
    @Override
    public Swagger handle(Swagger swagger) {
        System.out.println("handle swagger");

//        swagger.setHost("http://localhost:8084?info=add_by_extension");

        return swagger;
    }
}

@Bean
SwaggerUIConfiguration _swaggerUIConfiguration()
{
    var uiConfig = new SwaggerUIConfiguration();
//  uiConfig.setDefaultModelRendering("model");
    uiConfig.setDefaultModelExpandDepth(100);
//  uiConfig.setDocExpansion("full");
    return uiConfig;
    }

/**
 * 配置swagger-ui
 * 具体配置参考官网：https://github.com/swagger-api/swagger-ui/blob/master/docs/usage/configuration.md
 */
@Data
public class SwaggerUIConfiguration {
    private boolean _deepLinking = true;
    private boolean _displayOperationId = false;
    private int _defaultModelsExpandDepth = 0;
    private int _defaultModelExpandDepth = 100;
    private String _defaultModelRendering = "example";
    private boolean _displayRequestDuration = true;

    private String _docExpansion = "none";
    private int _maxDisplayedTags;
    private boolean _showExtensions;
    private boolean _showCommonExtensions;

    private String _layout = "StandaloneLayout";
}

```
其中 fieldPrefix表示字段前缀。
因为在获取javadoc时，会从field、get方法、set方法上获取，因此如果field有前缀，需要通过fieldPrefix设置，否则将无法获取到javadoc。
如：

```java
public class Response {
    /**
    * name javadoc
    */
    private String _name;
    public String getName() {
           return _name;
    }
    public void setName(String name) {
        _name = name;
    }
}
```
Name属性对应的字段是_name，因此 fieldPrefix应该设置为 `.fieldPrefix("_")`


## 原理

通过注解处理器在编译时生成javadoc的json文件, 将这些文件转换为Swagger-ui的OpenApi数据格式。

##原项目地址：https://github.com/Willing-Xyz/RestDoc