package model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import java.util.Objects;

@Entity
public class Student {
	public static Student currentLoginUser=null;
	
    private int studentId;//学生学号
    private String studentName;//学生姓名
    private String studentPwd;//学生密码
    private String studentSex;//学生性别
    private String studentMajor;//学生专业
    private String studentClass;//学生班级
    private String studentPhone;//学生手机号码
    private ErrorType errorType;

    public Student(){
    	this.studentName="";
    	this.studentSex="";
    	this.studentMajor="";
    	this.studentClass="";
    	this.studentPhone="";
    	this.errorType=new ErrorType();
    }
    public ErrorType getErrorType() {
		return errorType;
	}

	public void setErrorType(ErrorType errorType) {
		this.errorType = errorType;
	}
    @Id
    @Column(name = "StudentId")
    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    @Basic
    @Column(name = "StudentName")
    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    @Basic
    @Column(name = "StudentPwd")
    public String getStudentPwd() {
        return studentPwd;
    }

    public void setStudentPwd(String studentPwd) {
        this.studentPwd = studentPwd;
    }

    @Basic
    @Column(name = "StudentSex")
    public String getStudentSex() {
        return studentSex;
    }

    public void setStudentSex(String studentSex) {
        this.studentSex = studentSex;
    }

    @Basic
    @Column(name = "StudentMajor")
    public String getStudentMajor() {
        return studentMajor;
    }

    public void setStudentMajor(String studentMajor) {
        this.studentMajor = studentMajor;
    }

    @Basic
    @Column(name = "StudentClass")
    public String getStudentClass() {
        return studentClass;
    }

    public void setStudentClass(String studentClass) {
        this.studentClass = studentClass;
    }

    @Basic
    @Column(name = "StudentPhone")
    public String getStudentPhone() {
        return studentPhone;
    }

    public void setStudentPhone(String studentPhone) {
        this.studentPhone = studentPhone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return studentId == student.studentId &&
                Objects.equals(studentName, student.studentName) &&
                Objects.equals(studentPwd, student.studentPwd) &&
                Objects.equals(studentSex, student.studentSex) &&
                Objects.equals(studentMajor, student.studentMajor) &&
                Objects.equals(studentClass, student.studentClass) &&
                Objects.equals(studentPhone, student.studentPhone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentId, studentName, studentPwd, studentSex, studentMajor, studentClass, studentPhone);
    }
}
