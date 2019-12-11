package model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
public class Notice {
    private int noticeId;//公告编号
    private String noticeOption;//公告标题
    private String noticeContent;//公告内容
    private Timestamp noticeTime;//公告发布时间
    private Timestamp noticeEndTime;//公告结束时间
    private Club clubByClubId;//社团编号

    @Id
    @Column(name = "NoticeId")
    public int getNoticeId() {
        return noticeId;
    }

    public void setNoticeId(int noticeId) {
        this.noticeId = noticeId;
    }

    @Basic
    @Column(name = "NoticeOption")
    public String getNoticeOption() {
        return noticeOption;
    }

    public void setNoticeOption(String noticeOption) {
        this.noticeOption = noticeOption;
    }

    @Basic
    @Column(name = "NoticeContent")
    public String getNoticeContent() {
        return noticeContent;
    }

    public void setNoticeContent(String noticeContent) {
        this.noticeContent = noticeContent;
    }

    @Basic
    @Column(name = "NoticeTime")
    public Timestamp getNoticeTime() {
        return noticeTime;
    }

    public void setNoticeTime(Timestamp noticeTime) {
        this.noticeTime = noticeTime;
    }

    @Basic
    @Column(name = "NoticeEndTime")
    public Timestamp getNoticeEndTime() {
        return noticeEndTime;
    }

    public void setNoticeEndTime(Timestamp noticeEndTime) {
        this.noticeEndTime = noticeEndTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Notice notice = (Notice) o;
        return noticeId == notice.noticeId &&
                Objects.equals(noticeOption, notice.noticeOption) &&
                Objects.equals(noticeContent, notice.noticeContent) &&
                Objects.equals(noticeTime, notice.noticeTime) &&
                Objects.equals(noticeEndTime, notice.noticeEndTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(noticeId, noticeOption, noticeContent, noticeTime, noticeEndTime);
    }

    @ManyToOne
    @JoinColumn(name = "ClubId", referencedColumnName = "ClubId")
    public Club getClubByClubId() {
        return clubByClubId;
    }

    public void setClubByClubId(Club clubByClubId) {
        this.clubByClubId = clubByClubId;
    }
}
