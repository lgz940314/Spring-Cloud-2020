package com.krupp.cloud.common.api.user;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient("user-center")
public interface UserApi {

    @GetMapping("/1/2/3/4")
    String getUser();

}
