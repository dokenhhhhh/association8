package control;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import itf.INoticeControl;
import model.Club;
import model.ErrorType;
import model.Notice;
import util.BaseException;
import util.DbException;

public class NoticeControl implements INoticeControl{

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
	public ErrorType addNotice(String noticeOption, String noticeContent,Club club) throws BaseException {
		ErrorType errorType=new ErrorType();
		if(noticeOption==null || "".equals(noticeOption)) {
			errorType.setErrorId(14);//公告标题不能为空!
			return errorType;
		}
		if(noticeContent==null || "".equals(noticeContent)) {
			errorType.setErrorId(15);//公告内容不能为空!
			return errorType;
		}
		Notice notice=new Notice();
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		try {
			int noticeid=0;
			Query query=session.createQuery("select max(noticeId) from model.Notice");
			if(query.uniqueResult()!=null)
				noticeid=(int)query.uniqueResult();
			noticeid++;
			notice.setNoticeId(noticeid);
			notice.setNoticeOption(noticeOption);
			notice.setNoticeContent(noticeContent);
			notice.setNoticeTime(new java.sql.Timestamp(new java.util.Date().getTime()));
			notice.setClubByClubId(club);
			session.save(notice);
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
	public ErrorType changeNotice(Notice notice, String noticeOption, String noticeContent)
			throws BaseException {
		ErrorType errorType=new ErrorType();
		if(noticeOption==null || "".equals(noticeOption)) {
			errorType.setErrorId(14);//公告标题不能为空!
			return errorType;
		}
		if(noticeContent==null || "".equals(noticeContent)) {
			errorType.setErrorId(15);//公告内容不能为空!
			return errorType;
		}
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		try {
			notice.setNoticeOption(noticeOption);
			notice.setNoticeContent(noticeContent);
			session.update(notice);
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
	public ErrorType deleteNotice(Notice notice) throws BaseException {
		ErrorType errorType=new ErrorType();
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		try {
			session.delete(notice);
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
	public List<Notice> loadNotice() throws BaseException {
		List<Notice> result=new ArrayList<>();
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		try {
			result=session.createQuery("from model.Notice").list();
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
