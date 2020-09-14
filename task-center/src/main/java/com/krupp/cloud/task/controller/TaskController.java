package com.krupp.cloud.task.controller;

import com.krupp.cloud.task.domain.TaskBaseInfo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/taskCenter/task")
public class TaskController {

    @GetMapping("getTask")
    public TaskBaseInfo getTask(){
        TaskBaseInfo taskBaseInfo = new TaskBaseInfo();
        taskBaseInfo.setId(1);
        taskBaseInfo.setTaskName("测试任务:"+System.currentTimeMillis());
        System.out.println(taskBaseInfo.toString());
        return taskBaseInfo;
    }

    @GetMapping("saveTask")
    public TaskBaseInfo saveTask(String taskName){
        TaskBaseInfo taskBaseInfo = new TaskBaseInfo();
        taskBaseInfo.setId(2);
        taskBaseInfo.setTaskName("测试任务:"+taskName);
        System.out.println(taskBaseInfo.toString());
        return taskBaseInfo;
    }

}
