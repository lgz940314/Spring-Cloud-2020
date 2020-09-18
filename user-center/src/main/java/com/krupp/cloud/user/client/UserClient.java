package com.krupp.cloud.user.client;

import com.krupp.cloud.common.api.user.UserApi;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserClient implements UserApi {

    @Override
    public String getUser() {
        return "啊嘞嘞,居然跑通了,好神奇!";
    }
}
