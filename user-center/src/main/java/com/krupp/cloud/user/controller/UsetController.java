package com.krupp.cloud.user.controller;

import com.krupp.cloud.task.domain.TaskBaseInfo;
import com.krupp.cloud.task.api.TaskApi;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/user")
public class UsetController {

    @Resource
    TaskApi taskApi;

    @GetMapping("getTask")
    public String getTask(){
        TaskBaseInfo task = taskApi.getTask();
        return task.toString();
    }

    @GetMapping("saveTask")
    public String saveTask(String taskName){
        TaskBaseInfo task = taskApi.saveTask(taskName);
        return task.toString();
    }

}
