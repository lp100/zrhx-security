package org.com.zrhx.model;

import org.com.zrhx.enity.BaseEntity;

import java.util.Date;

public class ScheduleJobLog extends BaseEntity<ScheduleJobLog> {
    private String logID;

    private String jobID;

    private String beanName;

    private String methodName;

    private String params;

    private Integer satus;

    private String error;

    private Integer times;

    private Date createDate;

    public String getLogID() {
        return logID;
    }

    public void setLogID(String logID) {
        this.logID = logID == null ? null : logID.trim();
    }

    public String getJobID() {
        return jobID;
    }

    public void setJobID(String jobID) {
        this.jobID = jobID == null ? null : jobID.trim();
    }

    public String getBeanName() {
        return beanName;
    }

    public void setBeanName(String beanName) {
        this.beanName = beanName == null ? null : beanName.trim();
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName == null ? null : methodName.trim();
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params == null ? null : params.trim();
    }

    public Integer getSatus() {
        return satus;
    }

    public void setSatus(Integer satus) {
        this.satus = satus;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error == null ? null : error.trim();
    }

    public Integer getTimes() {
        return times;
    }

    public void setTimes(Integer times) {
        this.times = times;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}