package model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
public class Member {
    private int memberId;//成员编号
    private Integer memberNum;//成员学号
    private String memberName;//成员姓名
    private String post;//职务
    private String memberPhone;//成员电话号码
    private Timestamp enterTime;//入社时间
    private Timestamp leaveTime;//离社时间
    private Club clubByClubId;//社团

    public Member() {
		this.post="社员";
	}
    @Id
    @Column(name = "MemberId")
    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    @Basic
    @Column(name = "MemberNum")
    public Integer getMemberNum() {
        return memberNum;
    }

    public void setMemberNum(Integer memberNum) {
        this.memberNum = memberNum;
    }

    @Basic
    @Column(name = "MemberName")
    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    @Basic
    @Column(name = "Post")
    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    @Basic
    @Column(name = "MemberPhone")
    public String getMemberPhone() {
        return memberPhone;
    }

    public void setMemberPhone(String memberPhone) {
        this.memberPhone = memberPhone;
    }

    @Basic
    @Column(name = "EnterTime")
    public Timestamp getEnterTime() {
        return enterTime;
    }

    public void setEnterTime(Timestamp enterTime) {
        this.enterTime = enterTime;
    }

    @Basic
    @Column(name = "LeaveTime")
    public Timestamp getLeaveTime() {
        return leaveTime;
    }

    public void setLeaveTime(Timestamp leaveTime) {
        this.leaveTime = leaveTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Member member = (Member) o;
        return memberId == member.memberId &&
                Objects.equals(memberNum, member.memberNum) &&
                Objects.equals(memberName, member.memberName) &&
                Objects.equals(post, member.post) &&
                Objects.equals(memberPhone, member.memberPhone) &&
                Objects.equals(enterTime, member.enterTime) &&
                Objects.equals(leaveTime, member.leaveTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(memberId, memberNum, memberName, post, memberPhone, enterTime, leaveTime);
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
