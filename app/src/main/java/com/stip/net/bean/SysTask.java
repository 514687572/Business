package com.stip.net.bean;

import java.io.Serializable;
import java.util.Date;


public class SysTask implements Serializable {
    private String taskId;

    private String taskUrl;

    private Integer taskCount;

    private String taskStatus;

    //private Date taskTime;

    private String taskBusinessName;

    private String hostName;

    private String taskRemark;

    private static final long serialVersionUID = 1L;

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getTaskUrl() {
        return taskUrl;
    }

    public void setTaskUrl(String taskUrl) {
        this.taskUrl = taskUrl;
    }

    public Integer getTaskCount() {
        return taskCount;
    }

    public void setTaskCount(Integer taskCount) {
        this.taskCount = taskCount;
    }

    public String getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(String taskStatus) {
        this.taskStatus = taskStatus;
    }

    public String getTaskBusinessName() {
        return taskBusinessName;
    }

    public void setTaskBusinessName(String taskBusinessName) {
        this.taskBusinessName = taskBusinessName;
    }

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public String getTaskRemark() {
        return taskRemark;
    }

    public void setTaskRemark(String taskRemark) {
        this.taskRemark = taskRemark;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", taskId=").append(taskId);
        sb.append(", taskUrl=").append(taskUrl);
        sb.append(", taskCount=").append(taskCount);
        sb.append(", taskStatus=").append(taskStatus);
        sb.append(", taskBusinessName=").append(taskBusinessName);
        sb.append(", hostName=").append(hostName);
        sb.append(", taskRemark=").append(taskRemark);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        SysTask other = (SysTask) that;
        return (this.getTaskId() == null ? other.getTaskId() == null : this.getTaskId().equals(other.getTaskId()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getTaskId() == null) ? 0 : getTaskId().hashCode());
        return result;
    }
}