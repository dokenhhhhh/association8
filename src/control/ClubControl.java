package control;

import java.io.Serializable;
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

import itf.IClubControl;
import model.Club;
import model.ErrorType;
import model.Student;
import model.Member;
import util.BaseException;

import util.DbException;

public class ClubControl implements IClubControl{
	
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
	public ErrorType addClub(int studentid,String clubName, String clubContebt) throws BaseException {
		ErrorType errorType=new ErrorType();
		if(clubName==null || "".equals(clubName)) {
			errorType.setErrorId(4);//社团名称不能为空!
			return errorType;
		}
		if(clubContebt==null || "".equals(clubContebt)) {
			errorType.setErrorId(5);//社团描述不能为空!
			return errorType;
		}
		Club club = new Club();
		Student user = new Student();
		
		Session session = getSession();
		Query query=session.createQuery("from model.Member where memberNum="+studentid+"");
		System.out.println();
		if(query.uniqueResult()!=null) {
			errorType.setErrorId(24);//已拥有社团!
			return errorType;
		}
		user=session.get(Student.class, studentid);
		Transaction transaction = session.beginTransaction();
	    try {
	    	new ApplicationControl().addApplication(studentid, clubContebt, "社团创建申请" , clubName);
	    	Query query1=session.createQuery("from model.Club where clubName = '"+clubName+"'");
	    	if(query1.list().size()!=0){
				errorType.setErrorId(6);//社团名称已存在!
				return errorType;
			}
	    	int clubid=0;
			Query query2=session.createQuery("select max(clubId) from model.Club");
			if(query2.uniqueResult()!=null)
				clubid=(int)query2.uniqueResult();
			clubid++;
			
            club.setClubId(clubid);
            club.setClubName(clubName);
            club.setNumber(0);
            club.setClubContebt(clubContebt);
            club.setPresidentnum(user.getStudentId());
            session.merge(club);
		    transaction.commit();
		   
		    errorType=new MemberControl().addMember(clubid, user.getStudentName(), user.getStudentId(), user.getStudentPhone(),"");
		    Member member=(Member) session.createQuery("from model.Member where memberNum="+user.getStudentId()+"").uniqueResult();
		    errorType=new MemberControl().changeMember(member);
		    session.close();
	    }catch(Exception e){
			e.printStackTrace();
			transaction.rollback();
			throw new DbException(e);
	    }
	    return errorType;
	}

	@Override
	public ErrorType changeClub(Club club,String clubName, String clubContebt) throws BaseException {
		ErrorType errorType=new ErrorType();
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
	    try {
	    	Query query1=session.createQuery("from model.Club where clubId != "+club.getClubId()+" and clubName = '"+clubName+"'");
	    	if(query1.list().size()!=0){
				errorType.setErrorId(6);//社团名称已存在!
				return errorType;
			}
	    	if(!(clubName==null || "".equals(clubName))) {
	    		club.setClubName(clubName);
			}
	    	if(!(clubContebt==null || "".equals(clubContebt))){
	    		club.setClubContebt(clubContebt);
			}
			session.update(club);
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
	public List<Club> loadClub() throws BaseException{
		List<Club> result=new ArrayList<Club>();
		Session session=getSession();
		Transaction transaction=session.beginTransaction();
		try{
			result=session.createQuery("from model.Club where clubCreationTime !=null").list();
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
	public ErrorType deleteClub(Club club) throws BaseException{
		ErrorType errorType=new ErrorType();
		Session session=getSession();
		Transaction transaction=session.beginTransaction();
		try{
			List<Integer> list = new ArrayList<Integer>();
			list = session.createQuery("select memberId from model.Member where ClubId = "+club.getClubId()+"").list();
			for(int i=0;i<list.size();i++) {
				Member member = session.load(Member.class, list.get(i));
				session.delete(member);
			}
            session.delete(club);
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
	public ErrorType approveClub(Club club) throws BaseException {
		ErrorType errorType=new ErrorType();
		Session session=getSession();
		Transaction transaction=session.beginTransaction();
		try{
			Date nowdate=new Date();
			SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
			club.setClubCreationTime(Timestamp.valueOf(simpleDate.format(nowdate)));
			session.merge(club);
			Member member=(Member) session.createQuery("from model.Member where memberNum="+club.getPresidentnum()+"").uniqueResult();
			new MemberControl().approveMember(member);
			transaction.commit();
			session.close();
		}catch(Exception e){
        	e.printStackTrace();
			throw new DbException(e);
        }
		return errorType;
	}

	@Override
	public ErrorType vetoClub(Club club) throws BaseException {
		ErrorType errorType=new ErrorType();
		Session session=getSession();
		Transaction transaction=session.beginTransaction();
		try{
			session.delete(club);
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
	public List<Club> loadClubByAdmin() throws BaseException {
		List<Club> result=new ArrayList<Club>();
		Session session=getSession();
		Transaction transaction=session.beginTransaction();
		try{
			result=session.createQuery("from model.Club where clubCreationTime =null").list();
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
	public Club getClubByStudent(Student student) throws BaseException {
		Club club=new Club();
		Session session=getSession();
		Transaction transaction=session.beginTransaction();
		try {
			Query query=session.createQuery("select clubByClubId from model.Member where memberNum="+student.getStudentId()+"");
			if(query.uniqueResult()!=null)
				 club=(Club)query.uniqueResult();
			else
				return null;
			transaction.commit();
			session.close();
		}catch(Exception e){
        	e.printStackTrace();
			transaction.rollback();
			throw new DbException(e);
        }
		return club;
	}
	public List<Club> loadClubByKey(String key) throws BaseException {
		List<Club> result=new ArrayList<Club>();
		Session session=getSession();
		Transaction transaction=session.beginTransaction();
		try{
			result=session.createQuery("from model.Club where clubName like '%"+key+"%'").list();
			transaction.commit();
			session.close();
		}catch(Exception e){
        	e.printStackTrace();
			transaction.rollback();
			throw new DbException(e);
        }
		return result;
	}
	public Club getClubByName(String name) throws BaseException{
		Club club=new Club();
		Session session=getSession();
		Transaction transaction=session.beginTransaction();
		try {
			Query query=session.createQuery("from model.Club where clubName='"+name+"'");
			club=(Club) query.uniqueResult();
		}catch(Exception e){
        	e.printStackTrace();
			transaction.rollback();
			throw new DbException(e);
        }
		
		return club;
	}
	public void ClubMumAdd(Club club) throws BaseException{
		Session session=getSession();
		Transaction transaction=session.beginTransaction();
		try {
			club.setNumber(club.getNumber()+1);
			session.update(club);
			transaction.commit();
		}catch(Exception e){
        	e.printStackTrace();
			transaction.rollback();
			throw new DbException(e);
        }
	}
	
	public void ClubMumDel(Club club) throws BaseException{
		Session session=getSession();
		Transaction transaction=session.beginTransaction();
		try {
			club.setNumber(club.getNumber()-1);
			session.update(club);
			transaction.commit();
		}catch(Exception e){
        	e.printStackTrace();
			transaction.rollback();
			throw new DbException(e);
        }
	}
	
	public static void main(String[] args) throws BaseException {
		new ClubControl().addClub(31701019, "nn", "");
		}
}
