package model;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Task {
    private int taskId;//任务编号
    private String assignmentName;//分配人姓名
    private String taskContent;//任务内容
    private Member memberByMemberId;//社员
    private Club clubByClubId;//社团
    private Activity activityByActivityId;//活动

    @Id
    @Column(name = "TaskId")
    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    @Basic
    @Column(name = "AssignmentName")
    public String getAssignmentName() {
        return assignmentName;
    }

    public void setAssignmentName(String assignmentName) {
        this.assignmentName = assignmentName;
    }

    @Basic
    @Column(name = "TaskContent")
    public String getTaskContent() {
        return taskContent;
    }

    public void setTaskContent(String taskContent) {
        this.taskContent = taskContent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return taskId == task.taskId &&
                Objects.equals(assignmentName, task.assignmentName) &&
                Objects.equals(taskContent, task.taskContent);
    }

    @Override
    public int hashCode() {
        return Objects.hash(taskId, assignmentName, taskContent);
    }

    @ManyToOne
    @JoinColumn(name = "MemberId", referencedColumnName = "MemberId")
    public Member getMemberByMemberId() {
        return memberByMemberId;
    }

    public void setMemberByMemberId(Member memberByMemberId) {
        this.memberByMemberId = memberByMemberId;
    }

    @ManyToOne
    @JoinColumn(name = "ClubId", referencedColumnName = "ClubId")
    public Club getClubByClubId() {
        return clubByClubId;
    }

    public void setClubByClubId(Club clubByClubId) {
        this.clubByClubId = clubByClubId;
    }

    @ManyToOne
    @JoinColumn(name = "ActivityId", referencedColumnName = "ActivityId")
    public Activity getActivityByActivityId() {
        return activityByActivityId;
    }

    public void setActivityByActivityId(Activity activityByActivityId) {
        this.activityByActivityId = activityByActivityId;
    }
}
