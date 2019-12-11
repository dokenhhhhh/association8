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

import itf.IMemberControl;
import model.Club;
import model.ErrorType;
import model.Member;
import model.Student;
import util.BaseException;
import util.DbException;

public class MemberControl implements IMemberControl{

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
	public ErrorType addMember(int clubid,String MemberName,int MemberNum,String StudentPhone,String Reason) throws BaseException {
		ErrorType errorType=new ErrorType();
		Member member=new Member();
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		if(session.get(Student.class, MemberNum)==null) {
			errorType.setErrorId(18);//账号不存在!
			return errorType;
		}
		Query query1=session.createQuery("from model.Member where memberNum="+MemberNum+" ");
		if(query1.uniqueResult()!=null) {
			errorType.setErrorId(24);//已拥有社团!
			return errorType;
		}
		try {
			int memid=0;
			Query query=session.createQuery("select max(memberId) from model.Member");
			if(query.uniqueResult()!=null)
				memid=(int)query.uniqueResult();
			memid++;
			member.setMemberId(memid);
			member.setMemberName(MemberName);
			member.setMemberNum(MemberNum);
			member.setMemberPhone(StudentPhone);
			member.setPost("社员");
			Club club=(Club) session.createQuery("from model.Club where clubId="+clubid+"").uniqueResult();
			errorType=new ApplicationControl().addApplication(MemberNum, Reason, "加入社团申请",club.getClubName());
			member.setClubByClubId(club);
			session.save(member);
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
	public ErrorType changeMember(Member member) throws BaseException {
		ErrorType errorType=new ErrorType();
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		try {
			member.setPost("社长");
			session.update(member);
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
	public ErrorType deleteMember(Member member) throws BaseException {
		ErrorType errorType=new ErrorType();
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		try {
			session.delete(member);
			
			Club club=member.getClubByClubId();
			new ClubControl().ClubMumDel(club);
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
	public List<Member> loadMember(Club club) throws BaseException {
		List<Member> result=new ArrayList<Member>();
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		try {
			result=session.createQuery("from model.Member where clubByClubId = "+club.getClubId()+" and enterTime !=null").list();
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
	public ErrorType approveMember(Member member) throws BaseException {
		ErrorType errorType=new ErrorType();
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		try {
			Date nowdate=new Date();
			SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			member.setEnterTime(Timestamp.valueOf(simpleDate.format(nowdate)));
			session.merge(member);		
			Club club=member.getClubByClubId();
			new ClubControl().ClubMumAdd(club);
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
	public ErrorType vetoMember(Member member) throws BaseException {
		ErrorType errorType=new ErrorType();
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		try {
			session.delete(member);
			transaction.commit();
			session.close();
		}catch(Exception e){
			e.printStackTrace();
			transaction.rollback();
			throw new DbException(e);
	    }
		return errorType;
	}
public String getpost(int id) {
	Session session = getSession();
	Transaction transaction = session.beginTransaction();
	String result=(String)session.createQuery("select post from model.Member where memberNum = "+id+"").uniqueResult();
	transaction.commit();
	session.close();
	return result;
}
public Member getMemberByName(int name) throws BaseException{
	Member member=new Member();
	Session session=getSession();
	Transaction transaction=session.beginTransaction();
	try {
		Query query=session.createQuery("from model.Member where memberNum="+name+" ");
		member=(Member) query.uniqueResult();
		transaction.commit();
		session.close();
	}catch(Exception e){
    	e.printStackTrace();
		transaction.rollback();
		throw new DbException(e);
    }
	
	return member;
}
public List<Member> getMemberById(String num ,Club club) throws BaseException{
	List<Member> result=new ArrayList<Member>();
	Session session=getSession();
	Transaction transaction=session.beginTransaction();
	try {
		if(num==null||"".equals(num)) {
			result =session.createQuery("from model.Member").list();
		}
		else {
			result=session.createQuery("from model.Member where memberNum="+Integer.valueOf(num)+" or memberName like '%"+num+"%' and enterTime!=null and clubByClubId="+club.getClubId()+"").list();
		}
	}catch(Exception e){
    	e.printStackTrace();
		transaction.rollback();
		throw new DbException(e);
    }
	return result;
}
public void main(String []args) {
	 MemberControl m=new MemberControl();
	 String post=m.getpost(31701022);
	 System.out.println(post);
}
}
