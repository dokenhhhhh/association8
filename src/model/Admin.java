package model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
public class Admin {
    private int adminId;
    private String adminName;
    private String pwd;
    private Timestamp creationTime;
    private Timestamp logoffTime;
    private ErrorType errorType=new ErrorType();

    public ErrorType getErrorType() {
		return errorType;
	}

	public void setErrorType(ErrorType errorType) {
		this.errorType = errorType;
	}
    @Id
    @Column(name = "AdminId")
    public int getAdminId() {
        return adminId;
    }

    public void setAdminId(int adminId) {
        this.adminId = adminId;
    }

    @Basic
    @Column(name = "AdminName")
    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    @Basic
    @Column(name = "Pwd")
    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    @Basic
    @Column(name = "CreationTime")
    public Timestamp getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Timestamp creationTime) {
        this.creationTime = creationTime;
    }

    @Basic
    @Column(name = "LogoffTime")
    public Timestamp getLogoffTime() {
        return logoffTime;
    }

    public void setLogoffTime(Timestamp logoffTime) {
        this.logoffTime = logoffTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Admin admin = (Admin) o;
        return adminId == admin.adminId &&
                Objects.equals(adminName, admin.adminName) &&
                Objects.equals(pwd, admin.pwd) &&
                Objects.equals(creationTime, admin.creationTime) &&
                Objects.equals(logoffTime, admin.logoffTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(adminId, adminName, pwd, creationTime, logoffTime);
    }
}
