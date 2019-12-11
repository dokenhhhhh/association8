package itf;

import java.sql.Timestamp;
import java.util.List;

import model.Activity;
import model.Admin;
import model.ErrorType;
import model.Student;
import util.BaseException;

public interface IActivityControl {
	
	
	ErrorType changeActivity(Activity activity,String activityName,Timestamp startTime,Timestamp endTime,String activitySpace,Integer activityNum,String activityContent) throws BaseException;
	
	ErrorType deleteActivity(Activity activity) throws BaseException;
	
	List<Activity> loadActivities() throws BaseException;
	
	List<Activity> loadActivitiesByAdmin() throws BaseException;
	
	ErrorType approveActivity(Activity activity) throws BaseException;
	
	ErrorType vetoAcitvity(Activity activity) throws BaseException;

//	ErrorType addActivity(String activityName, String Clubname, Timestamp startTime, Timestamp endTime,
//			String activitySpace, Integer activityNum, String activityContent) throws BaseException;

//	ErrorType addActivity(String activityName, String Clubname, String activitySpace, String activityContent)
	//		throws BaseException;

//	ErrorType addActivity(String activityName, String spacename, String num, String starttime, String activityContent)
//			throws BaseException;

//	ErrorType addActivity(Student student, String activityName, String spacename, String num, String starttime,
//			String activityContent) throws BaseException;

	ErrorType addActivity(Admin admin, String activityName, String spacename, String num, String starttime,
			String activityContent) throws BaseException;
	
 }
