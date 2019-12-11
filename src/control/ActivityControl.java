package control;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import itf.IActivityControl;
import model.Activity;
import model.Admin;
import model.ErrorType;
import model.Student;
import util.BaseException;
import util.BusinessException;
import util.DbException;

public class ActivityControl implements  IActivityControl{

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
	public ErrorType addActivity(Admin admin,String activityName, String spacename,String num, String starttime,String activityContent)throws BaseException{
		ErrorType errorType=new ErrorType();
		if(activityName==null || "".equals(activityName)) {
			errorType.setErrorId(11);//活动名称不能为空!
			return errorType;
		}
		if(activityContent==null || "".equals(activityContent)) {
			errorType.setErrorId(13);//活动内容不能为空!
			return errorType;
		}
		if(num==null || "".equals(num)) {
			errorType.setErrorId(21);//活动人数不能为空!
			return errorType;
		}
		if(spacename==null || "".equals(spacename)) {
			errorType.setErrorId(21);//活动人数不能为空!
			return errorType;
		}
		Date startTime=null;
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		try{
			startTime=sdf.parse(starttime);
		}catch(Exception ex){
			errorType.setErrorId(20);//活动人数不能为空!
			return errorType;
			
		}
		
		Activity activity=new Activity();
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		try {
			
			Query query1=session.createQuery("select max(activityId) from model.Activity");
			int activityid=0;
			if(query1.uniqueResult()!=null)
				activityid=(int)query1.uniqueResult();
			activityid++;
	
			activity.setActivityId(activityid);
			activity.setStartTime(new java.sql.Timestamp(startTime.getTime()));
			activity.setActivityName(activityName);
			activity.setActivityNum(Integer.parseInt(num));
			activity.setActivityContent(activityContent);
			activity.setActivitySpace(spacename);
			session.save(activity);
			transaction.commit();
			if(admin!=null)
//			new ApplicationControl().addApplication(admin.getAdminId(), activityContent, "活动创建申请","");
		    session.close();
		}catch(Exception e){
			e.printStackTrace();
			transaction.rollback();
			throw new DbException(e);
	    }
	    return errorType;
	}
	public ErrorType addActivity(Student student,String activityName,String spacename, String num, String starttime,String activityContent)throws BaseException{
		ErrorType errorType=new ErrorType();
		if(activityName==null || "".equals(activityName)) {
			errorType.setErrorId(11);//活动名称不能为空!
			return errorType;
		}
		if(activityContent==null || "".equals(activityContent)) {
			errorType.setErrorId(13);//活动内容不能为空!
			return errorType;
		}
		if(num==null || "".equals(num)) {
			errorType.setErrorId(20);//活动人数不能为空!
			return errorType;
		}
		if(spacename==null || "".equals(spacename)) {
			errorType.setErrorId(21);//活动人数不能为空!
			return errorType;
		}
		Date startTime=null;
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		try{
			startTime=sdf.parse(starttime);
		}catch(Exception ex){
			throw new BusinessException("时间格式不正确，格式为：2018-01-22");
		}
		Activity activity=new Activity();
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		try {
			Query query1=session.createQuery("select max(activityId) from model.Activity");
			int activityid=0;
			if(query1.uniqueResult()!=null)
				activityid=(int)query1.uniqueResult();
			activityid++;
			activity.setActivityId(activityid);
			activity.setStartTime(new java.sql.Timestamp(startTime.getTime()));
			activity.setActivityName(activityName);
			activity.setActivityNum(Integer.parseInt(num));
			activity.setActivityContent(activityContent);
			activity.setActivitySpace(spacename);
			session.save(activity);
			new ApplicationControl().addApplication(student.getStudentId(), activityName, "活动创建申请", new ClubControl().getClubByStudent(student).getClubName());
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
	public ErrorType changeActivity(Activity activity, String activityName, Timestamp startTime, Timestamp endTime,
			String activitySpace, Integer activityNum, String activityContent) throws BaseException {
		ErrorType errorType=new ErrorType();
		if(activityName==null || "".equals(activityName)) {
			errorType.setErrorId(11);//活动名称不能为空!
			return errorType;
		}
		if(activitySpace==null || "".equals(activitySpace)) {
			errorType.setErrorId(12);//活动场地不能为空!
			return errorType;
		}
		if(activityContent==null || "".equals(activityContent)) {
			errorType.setErrorId(13);//活动内容不能为空!
			return errorType;
		}
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		try {
			activity.setStartTime(startTime);
			activity.setEndTime(endTime);
			activity.setActivitySpace(activitySpace);
			activity.setActivityName(activityName);
			if(!(activityNum==0 || "".equals(activityNum))) activity.setActivityNum(activityNum);
			activity.setActivityContent(activityContent);
			session.update(activity);
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
	public ErrorType deleteActivity(Activity activity) throws BaseException {
		ErrorType errorType=new ErrorType();
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		try {
			session.delete(activity);
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
	public List<Activity> loadActivities() throws BaseException {
		List<Activity> result=new ArrayList<Activity>();
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		try {
			result=session.createQuery("from model.Activity where activityState = '审核通过'").list();
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
	public ErrorType approveActivity(Activity activity) throws BaseException {
		ErrorType errorType=new ErrorType();
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		try {
			activity.setActivityState("审核通过");
			session.update(activity);
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
	public ErrorType vetoAcitvity(Activity activity) throws BaseException {
		ErrorType errorType=new ErrorType();
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		try {
			activity.setActivityState("审核不通过");
			session.update(activity);
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
	public List<Activity> loadActivitiesByAdmin() throws BaseException {
		List<Activity> result=new ArrayList<Activity>();
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		try {
			result=session.createQuery("from model.Activity where activityState = '审核中'").list();
			transaction.commit();
			session.close();
		}catch(Exception e){
			e.printStackTrace();
			transaction.rollback();
			throw new DbException(e);
	    }
		return result;
	}
	public ErrorType applyActivity(Student student,Activity activity) throws BaseException{
		ErrorType errorType=new ErrorType();
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		try {
			if(activity.getActivityNum()<=0) {
				errorType.setErrorId(21);//活动人数已满!"
				return errorType;
			}
			activity.setActivityNum(activity.getActivityNum()-1);
			transaction.commit();
			session.close();
			new ApplicationControl().addApplication(student.getStudentId(),"活动报名", "活动报名" , "");
		}catch(Exception e){
			e.printStackTrace();
			transaction.rollback();
			throw new DbException(e);
	    }
		return errorType;
	}
	public List<Activity> loadActivityByKey(String key) throws BaseException {
		List<Activity> result=new ArrayList<Activity>();
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		try {
			result=session.createQuery("from model.Activity where activityName like '%"+key+"%' and activityState='审核通过' ").list();
			transaction.commit();
			session.close();
		}catch(Exception e){
			e.printStackTrace();
			transaction.rollback();
			throw new DbException(e);
	    }
		return result;
	}
}
