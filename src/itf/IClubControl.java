package itf;

import java.util.List;


import model.Club;
import model.ErrorType;
import model.Student;
import util.BaseException;
import util.BusinessException;

public interface IClubControl {
	
//	ErrorType addClub(String clubName,String clubContebt) throws BaseException;
	
	ErrorType changeClub(Club club,String clubName,String clubContebt) throws BaseException;
	
	List<Club> loadClub() throws BaseException;
	
	List<Club> loadClubByAdmin() throws BaseException;
	
	ErrorType deleteClub(Club club) throws BaseException;
	
	ErrorType approveClub(Club club) throws BaseException;
	
	ErrorType vetoClub(Club club) throws BaseException;

	ErrorType addClub(int studentid, String clubName, String clubContebt) throws BaseException;

	Club getClubByStudent(Student student) throws BaseException;
}
