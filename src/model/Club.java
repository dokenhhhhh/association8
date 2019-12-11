package model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
public class Club {
    private int clubId;//社团编号
    private String clubName;//社团名称
    private Integer presidentnum;//社长学号
    private Integer number;//成员数量
    private Timestamp clubCreationTime;
    private String clubContebt;//社团描述
    private ErrorType errorType=new ErrorType();

    public ErrorType getErrorType() {
		return errorType;
	}

	public void setErrorType(ErrorType errorType) {
		this.errorType = errorType;
	}

	@Id
    @Column(name = "ClubId")
    public int getClubId() {
        return clubId;
    }

    public void setClubId(int clubId) {
        this.clubId = clubId;
    }

    @Basic
    @Column(name = "ClubName")
    public String getClubName() {
        return clubName;
    }

    public void setClubName(String clubName) {
        this.clubName = clubName;
    }

    @Basic
    @Column(name = "Presidentnum")
    public Integer getPresidentnum() {
        return presidentnum;
    }

    public void setPresidentnum(Integer presidentnum) {
        this.presidentnum = presidentnum;
    }

    @Basic
    @Column(name = "Number")
    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    @Basic
    @Column(name = "ClubCreationTime")
    public Timestamp getClubCreationTime() {
        return clubCreationTime;
    }

    public void setClubCreationTime(Timestamp clubCreationTime) {
        this.clubCreationTime = clubCreationTime;
    }

    @Basic
    @Column(name = "ClubContebt")
    public String getClubContebt() {
        return clubContebt;
    }

    public void setClubContebt(String clubContebt) {
        this.clubContebt = clubContebt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Club club = (Club) o;
        return clubId == club.clubId &&
                Objects.equals(clubName, club.clubName) &&
                Objects.equals(presidentnum, club.presidentnum) &&
                Objects.equals(number, club.number) &&
                Objects.equals(clubCreationTime, club.clubCreationTime) &&
                Objects.equals(clubContebt, club.clubContebt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(clubId, clubName, presidentnum, number, clubCreationTime, clubContebt);
    }
}
