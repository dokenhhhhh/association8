package itf;

import java.sql.Timestamp;
import java.util.List;

import model.Club;
import model.ErrorType;
import model.Notice;
import util.BaseException;

public interface INoticeControl {

	ErrorType addNotice(String noticeOption,String noticeContent,Club club) throws BaseException;
	
	ErrorType changeNotice(Notice notice,String noticeOption,String noticeContent) throws BaseException;
	
	ErrorType deleteNotice(Notice notice) throws BaseException;
	
	List<Notice> loadNotice() throws BaseException;
	
}
