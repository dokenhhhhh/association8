package model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
public class Activity {
    private int activityId;//活动编号
    private String activityName;//活动名称
    private Timestamp startTime;//开始时间
    private Timestamp endTime;//结束时间
    private String activitySpace;//活动地点
    private Integer activityNum;//活动人数
    private String activityContent;//活动内容
    private String activityState;//活动状态
    
    public Activity() {
		this.activityNum=1000;
		this.activityState="审核中";
	}
    @Id
    @Column(name = "ActivityId")
    public int getActivityId() {
        return activityId;
    }

    public void setActivityId(int activityId) {
        this.activityId = activityId;
    }

    @Basic
    @Column(name = "ActivityName")
    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    @Basic
    @Column(name = "StartTime")
    public Timestamp getStartTime() {
        return startTime;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    @Basic
    @Column(name = "EndTime")
    public Timestamp getEndTime() {
        return endTime;
    }

    public void setEndTime(Timestamp endTime) {
        this.endTime = endTime;
    }

    @Basic
    @Column(name = "ActivitySpace")
    public String getActivitySpace() {
        return activitySpace;
    }

    public void setActivitySpace(String activitySpace) {
        this.activitySpace = activitySpace;
    }

    @Basic
    @Column(name = "ActivityNum")
    public Integer getActivityNum() {
        return activityNum;
    }

    public void setActivityNum(Integer activityNum) {
        this.activityNum = activityNum;
    }

    @Basic
    @Column(name = "ActivityContent")
    public String getActivityContent() {
        return activityContent;
    }

    public void setActivityContent(String activityContent) {
        this.activityContent = activityContent;
    }

    @Basic
    @Column(name = "ActivityState")
    public String getActivityState() {
        return activityState;
    }

    public void setActivityState(String activityState) {
        this.activityState = activityState;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Activity activity = (Activity) o;
        return activityId == activity.activityId &&
                Objects.equals(activityName, activity.activityName) &&
                Objects.equals(startTime, activity.startTime) &&
                Objects.equals(endTime, activity.endTime) &&
                Objects.equals(activitySpace, activity.activitySpace) &&
                Objects.equals(activityNum, activity.activityNum) &&
                Objects.equals(activityContent, activity.activityContent) &&
                Objects.equals(activityState, activity.activityState);
    }

    @Override
    public int hashCode() {
        return Objects.hash(activityId, activityName, startTime, endTime, activitySpace, activityNum, activityContent, activityState);
    }
}
