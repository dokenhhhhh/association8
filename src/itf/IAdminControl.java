package itf;

import model.Admin;
import model.ErrorType;
import util.BaseException;

public interface IAdminControl {

	//Admin reg(String adminId, String pwd, String pwd2) throws BaseException;

	Admin login(String adminname, String pwd) throws BaseException;

//	ErrorType changePwd(Admin user, String newPwd, String newPwd2) throws BaseException;

	ErrorType changePwd(Admin user, String pwd, String newPwd, String newPwd2) throws BaseException;

}
