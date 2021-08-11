# @RequestMapping注解

## 作用：

```
 标识一个类：  设置映射请求的请求路径的初始信息
 标识一个方法： 设置映射请求的请求路径的具体信息
```



## 1. value属性

```
通过请求的请求地址匹配请求映射；
是一个字符串数组，表示该请求映射能够匹配多个请求地址所对应的请求；
当请求的请求地址不满足映射关系，浏览器报404错误
```



## 2.  method属性

```
通过请求的请求方式（GET/POST）匹配请求映射
不指定该属性时,任何请求方式都能匹配请求映射
当请求的请求地址满足请求映射的value属性，但请求方式不满足时，浏览器报405错误   Request Method 'POST' not supported
```



## 3.  params属性（了解）

```
通过请求的请求参数匹配请求映射。
请求的请求参数要求满足params属性列表中的所有关系
当请求的请求地址满足请求路径和请求方式而不满足请求参数时，浏览器报400错误
```



## 4.headers属性（了解）

```
通过请求的请求头信息匹配请求映射
例：@RequestMapping(
		headers = {Host = localhost:8080}
	)
若请求头信息不满足，页面报404错误
```

# SpringMVC支持ant风格的路径

```
使用在@RequestMapping注解中value属性的路径中
? : 代表任意的单个字符
* ：代表0个或多个字符
** : 代表任意的一层或多层目录

注意：在使用**时，只能使用/**/xxx的方式
```

# SpringMVC支持路径中的占位符（重点）

```
原始方式：/delete?id=1
rest方式：/delete/1

springmvc中的占位符常用于restful风格中，当请求路径中将某些数据通过路径的方式传输到服务器中
```

```java
    @RequestMapping("/path/{id}")
    public String testPath(@PathVariable("id") Integer id){
        System.out.println("id = " + id);
        return "success";
    }
```

# SpringMVC获取请求参数

## 1、通过ServletAPI获取

将**HttpServletRequest**作为控制器方法的形参，此时HttpServletRequest类型的参数表示封装了当前请求的请求报文的对象

```java
    @RequestMapping("/testServletAPI")
    //形参位置的request表示当前请求
    public String testServletAPI(HttpServletRequest request){
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        System.out.println("username = " + username + ", password = " + password);
        return "success";
     }
```

## 2、通过控制器方法的形参获取请求参数

在控制器方法的形参位置，设置和请求参数同名的形参，当浏览器发送请求时，匹配到请求映射时，在DispatcherServlet中就会将请求参数赋值给相应的形参

```html
<form th:action="@{/testController}" method="post">
    用户名：<input type="text" name="username" value="" /><br>
    密码：<input type="password" name="password" value="" /><br>
    爱好：<input type="checkbox" name="hobby" value="a"/>a
    <input type="checkbox" name="hobby" value="b"/>b
    <input type="checkbox" name="hobby" value="c"/>c
    <input type="submit" value="提交"/>
</form>
```



```java
    @RequestMapping("/testController")
		//多请求参数中出现同名的请求参数，可以在控制器方法的形参位置设置字符串类型或字符串数组接受此请求参数
		//字符串类型参数返回结果：a,b,c
    public String testServletAPI(String username, String password, String[] hobby){
        System.out.println("username = " + username + ", password = " + password + ", hobby = " + Arrays.toString(hobby));
        return "success";
    }
```

## 3、@RequestParam注解

**@RequestParam**注解是将请求参数和控制器方法的形参创建映射关系

**属性：** 

1.   **value：** 指定为形参赋值的请求参数的参数名
2.  **required：** 设置是否必须传输此请求参数。true时不传输该参数spring报错。false不传输该参数给默认值
3.  **defaultValue：**不论required属性值为是或否，当value指定的参数没有传输时，则使用该属性设置的默认值为形参赋值

```java
    @RequestMapping("/testController")
    public String testServletAPI(
            //@RequestParam("user_name") String username, 此时没有该user_name就报错
            @RequestParam(value = "user_name", required = false, defaultValue = "hehe") String username,
            String password,
            String[] hobby){
        System.out.println("username = " + username + ", password = " + password + ", hobby = " + Arrays.toString(hobby));
        return "success";
    }
```

