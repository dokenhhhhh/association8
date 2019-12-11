package control;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import itf.IStudentControl;
import model.Admin;
import model.Club;
import model.ErrorType;
import model.MD5;
import model.Student;
import util.BaseException;
import util.BusinessException;
import util.DbException;

public class StudentControl implements IStudentControl {
	
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
	public ErrorType addStudent(int studentId, String studentPwd1,String studentPwd2) throws BaseException {
		ErrorType errorType=new ErrorType();
		if(studentId==0 || "".equals(studentId)) {
			errorType.setErrorId(7);//学号不能为空!
			return errorType;
		}
		if(studentPwd1==null || "".equals(studentPwd1)){
			errorType.setErrorId(0);//密码不能为空!
			return errorType;
		}
		if(studentPwd2==null || "".equals(studentPwd2)){
			errorType.setErrorId(0);//密码不能为空!
			return errorType;
		}
		if(!studentPwd1.equals(studentPwd2)){
			errorType.setErrorId(1);//两次输入的密码不一致!
			return errorType;
		}
		Student student = new Student();
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		try {
	    	Student student1=session.get(Student.class, studentId);
	    	if(student1!=null) {
	    		errorType.setErrorId(8);//学号已存在!
	    		return errorType;
	    	}
            student.setStudentId(studentId);
            String pwd=new MD5().md5(studentPwd1, String.valueOf(studentId));
            student.setStudentPwd(pwd);
			session.save(student);
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
	public ErrorType ChangeStudent(Student student, String studentName, String studentSex, String studentMajor,
			String studentClass, String studentPhone) throws BaseException {
		ErrorType errorType=new ErrorType();
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		try {
			if(!(studentName==null || "".equals(studentName))){
				student.setStudentName(studentName);
			}
			if(!(studentSex==null || "".equals(studentSex))){
				student.setStudentSex(studentSex);
			}
			if(!(studentMajor==null || "".equals(studentMajor))){
				student.setStudentMajor(studentMajor);
			}
			if(!(studentClass==null || "".equals(studentClass))){
				student.setStudentClass(studentClass);
			}
			if(!(studentPhone==null || "".equals(studentPhone))){
				student.setStudentPhone(studentPhone);
			}
			session.update(student);
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
	public ErrorType ChangePwd(Student student, String pwd,String newpwd1, String newpwd2) throws BaseException {
		ErrorType errorType=new ErrorType();
		if(newpwd1==null || "".equals(newpwd1)){
			errorType.setErrorId(0);//密码不能为空!
			return errorType;
		}
		if(pwd==null || "".equals(pwd)){
			errorType.setErrorId(0);//密码不能为空!
			return errorType;
		}
		if(newpwd2==null || "".equals(newpwd2)){
			errorType.setErrorId(0);//密码不能为空!
			return errorType;
		}
		if(!newpwd1.equals(newpwd2)){
			errorType.setErrorId(1);//两次输入的密码不一致!
			return errorType;
		}
		try {
			if(!(new MD5().verify(pwd, String.valueOf(student.getStudentId()),student.getStudentPwd()))) {
				errorType.setErrorId(3);//密码错误
				return errorType;
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		try {
			String pwd1=new MD5().md5(newpwd1, String.valueOf(student.getStudentId()));
			student.setStudentPwd(pwd1);
			session.update(student);
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
	public Student LoginStudent(int studentId, String studentPwd) throws BaseException{
		Student student=new Student();
		if(studentId==0 || "".equals(studentId)) {
			student.getErrorType().setErrorId(7);//学号不能为空!
			return student;
		}
		if(studentPwd==null || "".equals(studentPwd)){
			student.getErrorType().setErrorId(0);//密码不能为空!
			return student;
		}
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		try {
			Student student1=session.get(Student.class, studentId);
			if(student1==null) {
				student.getErrorType().setErrorId(18);//账号不存在！
				return student;
			}
			if(!(new MD5().verify(studentPwd, String.valueOf(studentId), student1.getStudentPwd()))) {
				student.getErrorType().setErrorId(3);//密码错误!
				return student;
			}
		}catch(Exception e){
			e.printStackTrace();
			transaction.rollback();
			throw new DbException(e);
	    }
		return student;
	}
	public List<Student>  loadStudent() throws BaseException {
		List<Student> result=new ArrayList<Student>();
		Session session=getSession();
		Transaction transaction=session.beginTransaction();
		try{
			result=session.createQuery("from model.Student").list();
			transaction.commit();
			session.close();
		}catch(Exception e){
        	e.printStackTrace();
			transaction.rollback();
			throw new DbException(e);
        }
		return result;
	}
	public static void main(String[] args){
		MemberControl m=new MemberControl();
		 String post=m.getpost(31701022);
		 System.out.println(post);
	}
}
