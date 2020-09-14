package com.krupp.cloud.task.domain;

import lombok.Data;

import java.util.Date;

@Data
public class TaskBaseInfo {
    private static final long serialVersionUID = 1L;

    /**
     * 任务ID
     */
    private Integer id;

    /**
     * 任务编号
     */
    private String taskCode;

    /**
     * 任务名称
     */
    private String taskName;

    /**
     * 任务开始日期
     */
    private Date taskStartTime;

    /**
     * 任务截止日期
     */
    private Date taskEndTime;

    /**
     * 任务说明
     */
    private String remark;


}
