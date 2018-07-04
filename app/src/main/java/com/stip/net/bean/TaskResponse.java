package com.stip.net.bean;

import com.stip.net.base.StipResponse;

import java.util.List;

/**
 * Created by Administrator on 2018/7/3.
 */

public class TaskResponse extends StipResponse {
    public List<SysTask> taskList;

    public List<SysTask> getTaskList() {
        return taskList;
    }

    public void setTaskList(List<SysTask> taskList) {
        this.taskList = taskList;
    }
}
