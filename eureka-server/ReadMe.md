搭建单机版Eureka Server
1.引入依赖
Eureka已经被Spring Cloud继承在其子项目spring-cloud-netflix中，搭建Eureka Server的方式还是非常简单的。
只需要通过一个独立的maven工程即可搭建Eureka Server。
pom依赖如下
    
        <!-- spring cloud Eureka Server 启动器 -->
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-netflix-eureka-server</artifactId>
        </dependency>
    
2.修改yml配置
Eureka Server本身也是一个服务，同时又是一个注册中心。
在Spring Cloud中，启动的微服务会自动的搜索注册中心并注册服务，那么在单机版Eureka Server环境中，当前服务注册到当前服务中，明显是不合适的。
所以搭建Eureka Server单机版时，需要提供特殊的全局配置，避免回路注册逻辑。
同理，Eureka Server服务在注册中心中发现服务列表逻辑也是不必要的。
毕竟注册中心是一个中立的服务管理平台，如果是单机版Eureka Server环境中，Eureka Server服务再去发现服务列表，明显也是不必要的。也需要通过全局配置，避免回路发现逻辑。
    
    
    server:
      #设置Eureka Server WEB控制台端口，自定义
      port: 8080
    spring:
      application:
        #设置spring应用命名，可以自定义，非必要
        name: eureka-server
    eureka:
      client:
        #是否将自己注册到Eureka-Server中，默认的为true
        register-with-eureka: false
        #是否从Eureka-Server中获取服务注册信息，默认为true
        fetch-registry: false

3.启动Eureka Server注册中心，和普通的SpringBoot应用的启动没有太大的区别。只需要在启动类上增加@EnableEurekaServer注解，来开启Eureka Server服务即可。
注意：此处@SpringCloudApplication注解定义启动类。@SpringCloudApplication注解定义启动类涉及到hystrix相关内容。
      
    @EnableEurekaServer
    @SpringBootApplication
    public class EurekaApplication {
    
        public static void main(String[] args) {
            SpringApplication.run(EurekaApplication.class, args);
        }
    
    }