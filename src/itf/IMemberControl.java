package itf;

import java.util.List;

import model.Club;
import model.ErrorType;
import model.Member;
import util.BaseException;

public interface IMemberControl {

	
	ErrorType changeMember(Member member) throws BaseException;
	
	ErrorType deleteMember(Member member) throws BaseException;
	
	List<Member> loadMember(Club club) throws BaseException;
	
	ErrorType approveMember(Member member) throws BaseException;
	
	ErrorType vetoMember(Member member) throws BaseException;

//	ErrorType addMember(int clubid, String MemberName, int MemberNum, String StudentPhone) throws BaseException;

	ErrorType addMember(int clubid, String MemberName, int MemberNum, String StudentPhone, String Reason)
			throws BaseException;
}
