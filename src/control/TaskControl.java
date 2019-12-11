package control;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import itf.ITaskControl;
import model.Activity;
import model.Club;
import model.ErrorType;
import model.Member;
import model.Student;
import model.Task;
import util.BaseException;
import util.DbException;

public class TaskControl implements ITaskControl{

	private static Configuration cf=new Configuration().configure();
    private static SessionFactory sf=cf.buildSessionFactory();
    
    //方法返回session
    public static Session getSession(){
        return sf.openSession();
    }
    
    //关闭Session
    
    public static void CloseSession(){
        getSession().close();
    }
    
	@Override
	public ErrorType addTask(String taskContent, Member memberByMemberId, Club clubByClubId,
			Activity activityByActivityId) throws BaseException {
		ErrorType errorType=new ErrorType();
		if(taskContent==null || "".equals(taskContent)) {
			errorType.setErrorId(17);//任务内容不能为空!
			return errorType;
		}
		Task task=new Task();
		Session session = getSession();
		Transaction transaction = session.beginTransaction(); 
		try {
			int taskId=0;
			Query query=session.createQuery("select max(taskId) from model.Task");
			if(query.uniqueResult()!=null)
				taskId=(int)query.uniqueResult();
			taskId++;
			task.setTaskId(taskId);
			task.setTaskContent(taskContent);
			task.setAssignmentName(new Student().currentLoginUser.getStudentName());
			task.setClubByClubId(clubByClubId);
			task.setMemberByMemberId(memberByMemberId);
			task.setActivityByActivityId(activityByActivityId);
			session.save(task);
			transaction.commit();
			session.close();
		}catch(Exception e){
			e.printStackTrace();
			transaction.rollback();
			throw new DbException(e);
	    }
	    return errorType;
	}

	@Override
	public ErrorType ChangeTask(Task task, String taskContent) throws BaseException {
		ErrorType errorType=new ErrorType();
		if(taskContent==null || "".equals(taskContent)) {
			errorType.setErrorId(17);//任务内容不能为空!
			return errorType;
		}
		Session session = getSession();
		Transaction transaction = session.beginTransaction(); 
		try {
			task.setTaskContent(taskContent);
			task.setAssignmentName(new Student().currentLoginUser.getStudentName());
			session.save(task);
			transaction.commit();
			session.close();
		}catch(Exception e){
			e.printStackTrace();
			transaction.rollback();
			throw new DbException(e);
	    }
	    return errorType;
	}

	@Override
	public List<Task> loadTask() throws BaseException {
		List<Task> result=new ArrayList<>();
		Session session = getSession();
		Transaction transaction = session.beginTransaction(); 
		try {
			result=session.createQuery("from model.Task where MemberId = "+(new Student().currentLoginUser).getStudentId()+"").list();
			transaction.commit();
			session.close();
		}catch(Exception e){
			e.printStackTrace();
			transaction.rollback();
			throw new DbException(e);
	    }
		return result;
	}

	@Override
	public ErrorType deleteTask(Task task) throws BaseException {
		ErrorType errorType=new ErrorType();
		Session session = getSession();
		Transaction transaction = session.beginTransaction(); 
		try {
			session.delete(task);
			transaction.commit();
			session.close();
		}catch(Exception e){
			e.printStackTrace();
			transaction.rollback();
			throw new DbException(e);
	    }
		return errorType;
	}
	
}
