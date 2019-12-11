package model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
public class Application {
    private int applicationId;//申请编号
    private Integer applicationNum;//申请人学号
    private String applicationContent;//申请内容
    private Timestamp applicationTime;//申请时间
    private String applicationState;//申请状态
    private String applicationResult;//社团名
    private String applicationType;//申请类型

    @Id
    @Column(name = "ApplicationId")
    public int getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(int applicationId) {
        this.applicationId = applicationId;
    }

    @Basic
    @Column(name = "ApplicationNum")
    public Integer getApplicationNum() {
        return applicationNum;
    }

    public void setApplicationNum(Integer applicationNum) {
        this.applicationNum = applicationNum;
    }

    @Basic
    @Column(name = "ApplicationContent")
    public String getApplicationContent() {
        return applicationContent;
    }

    public void setApplicationContent(String applicationContent) {
        this.applicationContent = applicationContent;
    }

    @Basic
    @Column(name = "ApplicationTime")
    public Timestamp getApplicationTime() {
        return applicationTime;
    }

    public void setApplicationTime(Timestamp applicationTime) {
        this.applicationTime = applicationTime;
    }

    @Basic
    @Column(name = "ApplicationState")
    public String getApplicationState() {
        return applicationState;
    }

    public void setApplicationState(String applicationState) {
        this.applicationState = applicationState;
    }

    @Basic
    @Column(name = "ApplicationResult")
    public String getApplicationResult() {
        return applicationResult;
    }

    public void setApplicationResult(String applicationResult) {
        this.applicationResult = applicationResult;
    }

    @Basic
    @Column(name = "ApplicationType")
    public String getApplicationType() {
        return applicationType;
    }

    public void setApplicationType(String applicationType) {
        this.applicationType = applicationType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Application that = (Application) o;
        return applicationId == that.applicationId &&
                Objects.equals(applicationNum, that.applicationNum) &&
                Objects.equals(applicationContent, that.applicationContent) &&
                Objects.equals(applicationTime, that.applicationTime) &&
                Objects.equals(applicationState, that.applicationState) &&
                Objects.equals(applicationResult, that.applicationResult) &&
                Objects.equals(applicationType, that.applicationType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(applicationId, applicationNum, applicationContent, applicationTime, applicationState, applicationResult, applicationType);
    }
}
