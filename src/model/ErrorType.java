package model;

public class ErrorType {
	public static String error[]= {/*0*/"密码不能为空!",/*1*/"两次输入的密码不一致!",/*2*/"用户名不能为空!",/*3*/"密码错误!",
			/*4*/"社团名称不能为空!",/*5*/"社团描述不能为空!",/*6*/"社团名称已存在!",/*7*/"学号不能为空!",/*8*/"学号已存在!",
			/*9*/"姓名不能为空!",/*10*/"性别不能为空!",/*11*/"活动名称不能为空!",/*12*/"活动场地不能为空!",/*13*/"活动内容不能为空!",
			/*14*/"公告标题不能为空!",/*15*/"公告内容不能为空!",/*16*/"场地名称不能为空!",/*17*/"任务内容不能为空!",/*18*/"账号不存在!",/*19*/"活动社团不存在!",/*20*/"时间格式不正确，格式为：2018-01-22",
			/*21*/"活动人数不能为空!",/*22*/"活动人数已满!",/*23*/"已对该社团发出申请!",/*24*/"已拥有社团!"};
	private int errorId=-1;
	
	public int getErrorId() {
		return errorId;
	}
	public void setErrorId(int errorId) {
		this.errorId = errorId;
	}
	
//	public static void main(String[] args) {
//		System.out.println(new ErrorType().error[0]);
//	}
}
