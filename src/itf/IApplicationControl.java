package itf;

import java.sql.Timestamp;
import java.util.List;

import model.Application;
import model.ErrorType;
import model.Student;
import util.BaseException;

public interface IApplicationControl {
	
//	ErrorType addApplication(String applicationContent,String applicationType) throws BaseException;
	
	ErrorType ChangeApplicationContent(Application application,String applicationContent) throws BaseException;
	
	ErrorType ChangeApplicationStation(Application application,String applicationState) throws BaseException;
	
//	List<Application> loadApplication() throws BaseException;
	
	ErrorType deleteApplication(Application application) throws BaseException;

//	ErrorType addApplication(Integer applicationNum, String applicationContent, String applicationType)
//			throws BaseException;

	List<Application> loadApplication(Student student) throws BaseException;

	ErrorType addApplication(Integer applicationNum, String applicationContent, String applicationType, String clubid)
			throws BaseException;
}
