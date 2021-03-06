package com.krupp.cloud.task.api;

import com.krupp.cloud.task.domain.TaskBaseInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "task-center")
public interface TaskApi {

    @GetMapping("/task/getTask")
    TaskBaseInfo getTask();

    @GetMapping("/task/saveTask")
    TaskBaseInfo saveTask(@RequestParam String taskName);

}
