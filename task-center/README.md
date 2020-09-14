1、Feign简介
Feign是一个声明式的Web服务客户端，使用Feign可使得Web服务客户端的写入更加方便。
它具有可插拔注释支持，包括Feign注解和JAX-RS注解、Feign还支持可插拔编码器和解码器、Spring Cloud增加了对Spring MVC注释的支持，并HttpMessageConverters在Spring Web中使用了默认使用的相同方式。Spring Cloud集成了Ribbon和Eureka，在使用Feign时提供负载平衡的http客户端。

简单来说。feign就是用于微服务之间的调用。

2、Feign简单使用
1.引入pom依赖

        <dependency>
			<groupId>org.springframework.cloud</groupId>
			<artifactId>spring-cloud-starter-openfeign</artifactId>
		</dependency>

2.在项目启动类上添加 @EnableFeignClients 注解表示开启 Feign 的支持

    @EnableEurekaClient
    @SpringBootApplication
    @EnableFeignClients
    public class TaskCenterApplication {

	    public static void main(String[] args) {
		    SpringApplication.run(TaskCenterApplication.class, args);
	    }

    }

注意：

在启动类上加@EnableFeignClients注解，如果你的Feign接口定义跟你的启动类不在一个包名下，还需要制定扫描的包名@EnableFeignClients(basePackages = "")

3.接下来创建一个API接口，用来绑定服务

    @FeignClient(value = "task-center")
    public interface TaskApi {

        @GetMapping("/taskCenter/task/getTask")
        TaskBaseInfo getTask();

        @GetMapping("/taskCenter/task/saveTask")
        TaskBaseInfo saveTask(@RequestParam String taskName);

    }

注意：

1. 使用 @FeignClient注解将当前接口和服务绑定，task-center 是服务名，大小写不敏感；
2. 然后使用 SpringMVC 的 @GetMapping注解将方法和接口绑定在一起。需要注意的是，在 SpringMVC 中，在需要给参数设置默认值或者要求参数必填的情况下才需要用到 @RequestParam 注解，而在这里，这个注解一定要加。


