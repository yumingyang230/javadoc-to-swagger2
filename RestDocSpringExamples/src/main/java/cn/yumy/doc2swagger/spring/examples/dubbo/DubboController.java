package cn.yumy.doc2swagger.spring.examples.dubbo;

import cn.yumy.doc2swagger.spring.annotation.DubboApi;
import cn.yumy.doc2swagger.spring.annotation.DubboUri;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.constraints.NotNull;

/**
 * 自定义注解测试
 *
 * @author yumy
 * @version 1.0.0
 * @date：2020/5/19
 */
@DubboApi
@Validated
public class DubboController {

    /**
    * Description：[获取用户]
    *
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


    /**
    * Description：[根据id获取用户]
    *
    * @param Id  用户id
    * @return cn.yumy.doc2swagger.spring.examples.dubbo.DubboController.User
    * @throws
    */
    @DubboUri(name = "getUserById", method = RequestMethod.POST)
    public User getUserById(String Id){
        User result = new User();
        result.setName("王德发");
        result.setAge(18);
        return result;
    }

    class User {
        /**
         * 名字
         */
        @NotNull
        private String name;
        /**
         * 年龄
         */
        private Integer age;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getAge() {
            return age;
        }

        public void setAge(Integer age) {
            this.age = age;
        }
    }
}
