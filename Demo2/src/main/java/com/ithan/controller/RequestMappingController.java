package com.ithan.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import sun.awt.SunHints;

/**
 * @RequestMapping注解：
 *  标识一个类：  设置映射请求的请求路径的初始信息
 *  标识一个方法： 设置映射请求的请求路径的具体信息
 *  属性：
 *      value：  通过请求的请求地址匹配请求映射；
*                是一个字符串数组，表示该请求映射能够匹配多个请求地址所对应的请求；
*
 *       method:    通过请求的请求方式（GET/POST）匹配请求映射
 *                  不指定该属性时,任何请求方式都能匹配请求映射
 *                  当请求的请求地址满足请求映射的value属性，但请求方式不满足时。
 *                  浏览器报405错误   Request Method 'POST' not supported
 *
 *       param：     通过请求的请求参数匹配请求映射
*
*   @RequestMapping的派生注解：
 *      @GetMapping
 *      @PostMapping
 *      @PutMapping
 *      @DeleteMapping
 *
 *  常见的请求方式： GET POST PUT DELETE
 *  目前浏览器只支持GET和POST, 若在form表单提交时为method设置了其他请求方式的字符串，则按照
 *  默认的请求方式GET处理
 *
 *  若要发送PUT和DELETE请求，则需要通过Spring提供的HiddenHttpMethodFilter。 这部分将在RestFul部分提及
 *
 */

@Controller
//@RequestMapping("/test")
public class RequestMappingController {

    @RequestMapping(
            value = {"/request", "/mapping"},
            method = RequestMethod.POST)
    public String testRequstMapping(){
        return "success";
    }

    @RequestMapping(
            value = {"/request", "/mapping"})
    public String testRequstMapping1(){
        System.out.println("POST请求");
        return "success";
    }

    @RequestMapping(
            value = {"/param"},
            params = {"username=admin"})
    public String testRequstParam(){
        return "success";
    }

    @RequestMapping(
            value = {"/request/header"},
            headers = {"Host=localhost:8081"}
    )
    public String testHeader(){
        return "success";
    }

    @RequestMapping(
            //value = "/a?a/ant"
            value = "/a/**/ant"
    )
    public String testAnt(){
        return "success";
    }

    //@RequestMapping("/path/{id}")
    @RequestMapping("/path/{id}/{username}")
    public String testPath(@PathVariable("id") Integer id, @PathVariable("username") String username){
        System.out.println("id = " + id + "username = " + username);
        return "success";
    }

}
