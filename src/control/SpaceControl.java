package control;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import itf.ISpaceControl;
import model.ErrorType;
import model.Space;
import util.BaseException;
import util.DbException;

public class SpaceControl implements ISpaceControl{

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
	public ErrorType addSpace(String spaceName) throws BaseException {
		ErrorType errorType=new ErrorType();
		if(spaceName==null || "".equals(spaceName)) {
			errorType.setErrorId(16);//场地名称不能为空!
			return errorType;
		}
		Space space=new Space();
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		try {
			int spaceid=0;
			Query query=session.createQuery("select max(spaceId) from model.Space");
			if(query.uniqueResult()!=null)
				spaceid=(int)query.uniqueResult();
			spaceid++;
			space.setSpaceId(spaceid);
			space.setSpaceName(spaceName);
			session.save(space);
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
	public ErrorType changeSpace(Space space,String spaceName) throws BaseException {
		ErrorType errorType=new ErrorType();
		if(spaceName==null || "".equals(spaceName)) {
			errorType.setErrorId(16);//场地名称不能为空!
			return errorType;
		}
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		try {
			space.setSpaceName(spaceName);
			session.update(space);
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
	public ErrorType changeSpaceState(Space space) throws BaseException {
		ErrorType errorType=new ErrorType();
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		try {
			if(space.getSpaceState()=="空闲") space.setSpaceState("借出");
			if(space.getSpaceState()=="借出") space.setSpaceState("空闲");
			session.update(space);
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
	public ErrorType deleteSpace(Space space) throws BaseException {
		ErrorType errorType=new ErrorType();
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		try {
			session.delete(space);
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
	public List<Space> loadSpace() throws BaseException {
		List<Space> result=new ArrayList<Space>();
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		try {
			result=session.createQuery("from model.Space").list();
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
