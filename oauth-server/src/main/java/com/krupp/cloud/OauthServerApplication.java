package com.krupp.cloud;

import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

import java.time.LocalDate;
import java.time.LocalTime;

@SpringBootApplication
@EnableEurekaClient
public class OauthServerApplication {

    public static void main(String[] args)
    {
        SpringApplication.run(OauthServerApplication.class, args);
        LoggerFactory.getLogger(OauthServerApplication.class).info(
                "《《《《《《 ROBOT started up successfully at {} {} 》》》》》》", LocalDate.now(), LocalTime.now());
        System.out.println("(♥◠‿◠)ﾉﾞ  认证授权中心启动成功   ლ(´ڡ`ლ)ﾞ ");
    }


}
