package control;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import itf.IAdminControl;
import model.Admin;
import model.ErrorType;
import util.BaseException;
import util.BusinessException;
import util.DBUtil;
import util.DbException;
import util.HibernateUtil;

public class AdminControl implements IAdminControl{
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
    
//	@Override
//	public Admin reg(String adminName, String pwd,String pwd2) throws BaseException {
//		if(pwd==null || "".equals(pwd)) throw new BusinessException("密码不能为空!");
//		if(!pwd.equals(pwd2)) throw new BusinessException("两次输入的密码不一致!");
//		if(adminName==null || "".equals(adminName)) throw new BusinessException("用户名不能为空!");
//		Admin admin=new Admin();
//		Session session = getSession();
//		Transaction transaction = session.beginTransaction();
//	    try {
//	    	Query query1=session.createQuery("from model.Admin where adminName = '"+adminName+"'");
//	    	if(query1.list().size()!=0) throw new BusinessException("用户名已存在!");
//	    	int adminid=0;
//			Query query2=session.createQuery("select max(adminId) from model.Admin");
//			if(query2.uniqueResult()!=null)
//				adminid=(int)query2.uniqueResult();
//			adminid++;
//            admin.setAdminId(adminid);
//	    	admin.setAdminName(adminName);
//			admin.setPwd(pwd);
//			admin.setCreationTime(new java.sql.Timestamp(new java.util.Date().getTime()));
//			session.save(admin);
//		    transaction.commit();
//		    session.close();
//	    }catch(Exception e){
//			e.printStackTrace();
//			transaction.rollback();
//	        throw new DbException(e);
//	    }    
//		return admin;
//	}
	


    @Override
	public Admin login(String adminname, String pwd) throws BaseException {
		Admin user =new Admin(); 
		if(adminname==null||adminname.equals("")) {
			user.getErrorType().setErrorId(2);
			return user;
		}
		if(pwd==null||pwd.equals("")) {
			user.getErrorType().setErrorId(0);
			return user;
		}
		Session session = HibernateUtil.getSession();
	    Transaction transaction = session.beginTransaction();
	    try {
	    	Query query=session.createQuery("from model.Admin where adminName = '"+adminname+"'");
	    	Admin admin=new Admin();
	    	admin=(Admin)query.uniqueResult();
	        if(admin==null) {
	        	user.getErrorType().setErrorId(18);//账号不存在！
				return user;
	        }
	        user=(Admin)query.uniqueResult();
	        if(!user.getPwd().equals(pwd)){
				user.getErrorType().setErrorId(3);
				return user;
			}
	        transaction.commit();
	        session.close();
	    }catch(Exception e){
			e.printStackTrace();
			transaction.rollback();
	        throw new DbException(e);
	    }    
	return user;
	}


	@Override
	public ErrorType changePwd(Admin user, String pwd,String newPwd,
			String newPwd2) throws BaseException {
		ErrorType errorType=new ErrorType();
		if(newPwd==null || "".equals(newPwd)) {
			errorType.setErrorId(0);
			return errorType;
		}
		if(newPwd2==null || "".equals(newPwd2)) {
			errorType.setErrorId(0);
			return errorType;
		}
		if(!newPwd.equals(newPwd2)) {
			errorType.setErrorId(1);
			return errorType;
		}
		if(!user.getPwd().equals(pwd)) {
			errorType.setErrorId(3);
			return errorType;
		}
		Session session=HibernateUtil.getSession();   
		Transaction transaction = session.beginTransaction();
		try {
	        user.setPwd(newPwd);
	        session.update(user);
	        transaction.commit();
	        session.close();
		}catch(Exception e){
			e.printStackTrace();
			transaction.rollback();
	        throw new DbException(e);
	    }
		return errorType;
	}


	public static void main(String[] args){
		Admin admin=new Admin();
		
		try {
			new AdminControl().login("admin", "admin");
		}catch (BaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}}
