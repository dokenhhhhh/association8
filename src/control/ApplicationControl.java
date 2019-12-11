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

import itf.IApplicationControl;
import model.Activity;
import model.Application;
import model.Club;
import model.ErrorType;
import model.Student;
import util.BaseException;
import util.DbException;

public class ApplicationControl implements IApplicationControl{

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
	public ErrorType addApplication(Integer applicationNum,String applicationContent, String applicationType ,String clubname) throws BaseException {
		ErrorType errorType=new ErrorType();
		Application application=new Application();
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		if(applicationType.equals("加入社团申请")) {
			Query query=session.createQuery("from model.Application where applicationNum="+applicationNum+" and applicationResult='"+clubname+"'");
			if(query.uniqueResult()!=null) {
				errorType.setErrorId(23);//已对该社团发出申请!
				return errorType;
			}
		}
		try {
			int applicationId = 0;
			Query query=session.createQuery("select max(applicationId) from model.Application");
			if(query.uniqueResult()!=null)
				applicationId=(int)query.uniqueResult();
			applicationId++;
			application.setApplicationId(applicationId);
			application.setApplicationNum(applicationNum);
			application.setApplicationContent(applicationContent);
			application.setApplicationState("审核中");
			application.setApplicationType(applicationType);
			application.setApplicationResult(clubname);
			Date nowdate=new Date();
			SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			application.setApplicationTime(Timestamp.valueOf(simpleDate.format(nowdate)));
			session.save(application);
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
	public ErrorType ChangeApplicationContent(Application application, String applicationContent) throws BaseException {
		ErrorType errorType=new ErrorType();
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		try {
			application.setApplicationContent(applicationContent);
			session.update(application);
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
	public ErrorType ChangeApplicationStation(Application application, String applicationState) throws BaseException {
		ErrorType errorType=new ErrorType();
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		try {
			application.setApplicationState(applicationState);
			session.update(application);
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
	public List<Application> loadApplication(Student student) throws BaseException {
		List<Application> result=new ArrayList<Application>();
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		try {
			result=session.createQuery("from model.Application where applicationNum="+student.getStudentId()+"").list();
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
	public ErrorType deleteApplication(Application application) throws BaseException {
		ErrorType errorType=new ErrorType();
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		try {
			session.delete(application);
			transaction.commit();
			session.close();
		}catch(Exception e){
			e.printStackTrace();
			transaction.rollback();
			throw new DbException(e);
	    }
		return errorType;
	}

	public List<Application> loadApplicationForAdmain() throws BaseException {
		List<Application> result=new ArrayList<Application>();
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		try {
			result=session.createQuery("from model.Application where applicationType='活动创建申请' and applicationState='审核中' ").list();
			transaction.commit();
			session.close();
		}catch(Exception e){
			e.printStackTrace();
			transaction.rollback();
			throw new DbException(e);
	    }
		return result;
	}
	public List<Application> loadApplicationForAdmain1() throws BaseException {
		List<Application> result=new ArrayList<Application>();
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		try {
			result=session.createQuery("from model.Application where applicationType='社团创建申请' and applicationState='审核中'").list();
			transaction.commit();
			session.close();
		}catch(Exception e){
			e.printStackTrace();
			transaction.rollback();
			throw new DbException(e);
	    }
		return result;
	}
	public List<Application> loadApplicationForProprieter(Student student) throws BaseException {
		List<Application> result=new ArrayList<Application>();
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		try {
			Club club=new ClubControl().getClubByStudent(student);
			result=session.createQuery("from model.Application where applicationType='加入社团申请'  and applicationResult='"+club.getClubName()+"' and applicationState='审核中'").list();
			transaction.commit();
			session.close();
		}catch(Exception e){
			e.printStackTrace();
			transaction.rollback();
			throw new DbException(e);
	    }
		return result;
	}
	public List<Application> loadApplicationForProprieterByKey(Student student,String key) throws BaseException {
			List<Application> result=new ArrayList<Application>();
			Session session = getSession();
			Transaction transaction = session.beginTransaction();
			try {
				Club club=new ClubControl().getClubByStudent(student);
				if(!(key==null||"".equals(key))) {
					result=session.createQuery("from model.Application where applicationNum="+Integer.valueOf(key)+" and applicationType='加入社团申请'  and applicationResult='"+club.getClubName()+"' and applicationState='审核中'").list();
				}
				else
					result=session.createQuery("from model.Application where applicationType='加入社团申请'  and applicationResult='"+club.getClubName()+"' and applicationState='审核中'").list();
				transaction.commit();
				session.close();
			}catch(Exception e){
				e.printStackTrace();
				transaction.rollback();
				throw new DbException(e);
		    }
			return result;
		}


	public List<Application> loadAcitvityApplicationForAdmainByKey(String key) throws BaseException {
			List<Application> result=new ArrayList<Application>();
			Session session = getSession();
			Transaction transaction = session.beginTransaction();
			try {
				result=session.createQuery("from model.Application where activityName like '%"+key+"%' and applicationType='活动创建申请' and applicationState='审核中' ").list();
				transaction.commit();
				session.close();
			}catch(Exception e){
				e.printStackTrace();
				transaction.rollback();
				throw new DbException(e);
		    }
			return result;
		}


	public List<Application> loadClubApplicationForAdmainBykey(String key) throws BaseException {
			List<Application> result=new ArrayList<Application>();
			Session session = getSession();
			Transaction transaction = session.beginTransaction();
			try {
				result=session.createQuery("from model.Application where applicationResult like '%"+key+"%'and applicationType='社团创建申请' and applicationState='审核中'").list();
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
