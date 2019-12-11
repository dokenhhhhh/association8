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
import control.StudentControl;
import model.Activity;
import model.Admin;
import model.Application;
import model.Club;
import model.ErrorType;
import model.Student;
import util.BaseException;

/**
 * Servlet implementation class StudentLoginSerlet
 */
@WebServlet("/StudentLoginSerlet")
public class StudentLoginSerlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StudentLoginSerlet() {
        super();
        // TODO Auto-generated constructor stub
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
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("进入了post方法");
		if(request.getParameter("user").equals("")) {
			JOptionPane.showMessageDialog(null," 用户名不能为空");
			request.getRequestDispatcher("login.html").forward(request, response);
		}
		int username = Integer.parseInt(request.getParameter("user"));
		String pass = request.getParameter("pass");
		StudentControl con=new StudentControl();
		Session sess = getSession();
		ClubControl ccon=new ClubControl();
		ApplicationControl acon=new ApplicationControl();
		ActivityControl accon=new ActivityControl();
		try {
			List<Activity> Activityall=accon.loadActivities();
			 HttpSession session = request.getSession();
	            //把用户数据保存在session域对象中;
	           
	        session.setAttribute("Activityall", Activityall);
			Student admin=new Student();
			admin=con.LoginStudent(username, pass);
			if(admin.getErrorType().getErrorId()!=-1) {
				JOptionPane.showMessageDialog(null, new ErrorType().error[admin.getErrorType().getErrorId()]);
				request.getRequestDispatcher("login.html").forward(request, response);
			}
			else{
				System.out.println("输入正确");
				//正确，则进入另一个页面
				ClubControl con1=new ClubControl();
				List<Club> Allclub=ccon.loadClub();
				
				//创建session对象
	      
	            //把用户数据保存在session域对象中;
	           
	            session.setAttribute("loginName", username);
	            session.setAttribute("Allclub", Allclub);
	            int loginname=(int)request.getSession().getAttribute("loginName");
	            Student student=sess.get(Student.class, loginname);
	            session.setAttribute("student", student);
	            
	            String post=(String) sess.createQuery("select post from model.Member where memberNum="+loginname+"").uniqueResult();
	            session.setAttribute("post", post);
	            Student stu=(Student)request.getSession().getAttribute("student");
	            List<Application> ApplicationAllS=acon.loadApplication(stu);
	            session.setAttribute("ApplicationAllS", ApplicationAllS);
	            Club club=con1.getClubByStudent(student);
	           
	            session.setAttribute("club", club);
	            System.out.println(stu.getStudentId());
				request.getRequestDispatcher("homepage.html").forward(request, response);
				request.setAttribute("username", username); request.setAttribute("pass", pass);
			}
		} catch (BaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
