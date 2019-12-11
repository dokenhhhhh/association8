package itf;


import model.ErrorType;
import model.Student;
import util.BaseException;

public interface IStudentControl {
	
	
	
	ErrorType ChangeStudent(Student student,String studentName,String studentSex,String studentMajor,String studentClass,String studentPhone) throws BaseException;
	
//	ErrorType ChangePwd(Student student,String newpwd1,String newpwd2) throws BaseException;
	
	Student LoginStudent(int studentId,String studentPwd) throws BaseException;
 ErrorType addStudent(int studentId, String studentPwd1,String studentPwd2) throws BaseException ;

ErrorType ChangePwd(Student student, String pwd, String newpwd1, String newpwd2) throws BaseException;
}
