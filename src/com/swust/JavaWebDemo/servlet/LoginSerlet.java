package com.swust.JavaWebDemo.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.JOptionPane;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import control.ActivityControl;
import control.AdminControl;
import control.ApplicationControl;
import control.ClubControl;
import control.MemberControl;
import control.SpaceControl;
import control.StudentControl;
import model.Activity;
import model.Admin;
import model.Application;
import model.Club;
import model.ErrorType;
import model.Member;
import model.Space;
import model.Student;
import util.BaseException;

/**
 * Servlet implementation class LoginSerlet
 */
@WebServlet("/LoginSerlet")
public class LoginSerlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginSerlet() {
        super();
        // TODO Auto-generated constructor stub
    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	public static boolean isNumericZidai(String str) {
		for (int i = 0; i < str.length(); i++) {
		System.out.println(str.charAt(i));
		if (!Character.isDigit(str.charAt(i))) {
		return false;
		}
		}
		return true;
		}
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
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		//判断是否正确
		System.out.println("进入了post方法");
		if(request.getParameter("user").equals("")) {
			JOptionPane.showMessageDialog(null," 用户名不能为空");
			request.getRequestDispatcher("login.html").forward(request, response);
		}
		String username = request.getParameter("user");
		String pass = request.getParameter("pass");
		AdminControl admincontrol=new AdminControl();
		ActivityControl con1=new ActivityControl();
		StudentControl con2=new StudentControl();
		MemberControl mcon=new MemberControl();
		ApplicationControl con =new ApplicationControl();
		ClubControl con3 =new ClubControl();
		SpaceControl scon=new SpaceControl();
		 HttpSession session = request.getSession();
         //把用户数据保存在session域对象中
		 Session sess = getSession();
		try {
			
			Admin admin=new Admin();
			if(isNumericZidai(username)) {
				int username1 = Integer.parseInt(username);
				String post=mcon.getpost(username1);
				if(post.equals("社长")) {
					Student stu=new Student();
					stu=con2.LoginStudent(username1, pass);
					Student student=sess.get(Student.class, username1);
					if(stu.getErrorType().getErrorId()!=-1) {
						JOptionPane.showMessageDialog(null, new ErrorType().error[stu.getErrorType().getErrorId()]);
						request.getRequestDispatcher("login.html").forward(request, response);
					}
					else{
						session.setAttribute("loginName", username1);
						List<Application> ApplicationEnterClub = con.loadApplicationForProprieter(student);
						session.setAttribute("ApplicationEnterClub", ApplicationEnterClub);
						Club club =con3.getClubByStudent(student);
						List<Member> MemberAll=mcon.loadMember(club);
						List<Space> SpaceAll=scon.loadSpace();
						session.setAttribute("club", club);
						session.setAttribute("SpaceAll", SpaceAll);
						session.setAttribute("student", student);
						session.setAttribute("MemberAll", MemberAll);
						request.getRequestDispatcher("president/admin.jsp").forward(request, response);
					}}
				else {
					JOptionPane.showMessageDialog(null, "不是社长没有权限");
					request.getRequestDispatcher("login.html").forward(request, response);
				}
				}
			
			else {
			admin=admincontrol.login(username, pass);
			List<Student> StudentAll=con2.loadStudent();
			List<Club> ClubAll=con3.loadClub();
			if(admin.getErrorType().getErrorId()!=-1) {
				JOptionPane.showMessageDialog(null, new ErrorType().error[admin.getErrorType().getErrorId()]);
				request.getRequestDispatcher("login.html").forward(request, response);
			}
			else{
				session.setAttribute("StudentAll", StudentAll);
				session.setAttribute("admin", admin);
				session.setAttribute("ClubAll", ClubAll);
				List<Application> ApplicationAll = con.loadApplicationForAdmain1();
				
				session.setAttribute("ApplicationAll", ApplicationAll);
				System.out.println("输入正确");
				//正确，则进入另一个页面s
				List<Activity> ActivityAll = con1.loadActivities();
				//request.setAttribute("ActivityAll", ActivityAll);
				session.setAttribute("ActivityAll", ActivityAll);
				request.getRequestDispatcher("admin/admin.jsp").forward(request, response); 
				request.setAttribute("username", username); request.setAttribute("pass", pass);
			}
			}} catch (BaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
			
		
	}
	
}
