package itf;

import java.util.List;

import model.Activity;
import model.Club;
import model.ErrorType;
import model.Member;
import model.Task;
import util.BaseException;

public interface ITaskControl {

	ErrorType addTask(String taskContent,Member memberByMemberId,Club clubByClubId,Activity activityByActivityId) throws BaseException;
	
	ErrorType ChangeTask(Task task,String taskContent) throws BaseException;
	
	List<Task> loadTask() throws BaseException;
	
	ErrorType deleteTask(Task task) throws BaseException;
}
