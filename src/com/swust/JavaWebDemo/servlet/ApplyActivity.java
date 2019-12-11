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
import control.ApplicationControl;
import control.ClubControl;
import model.Activity;
import model.Application;
import model.ErrorType;
import model.Student;
import util.BaseException;

/**
 * Servlet implementation class ApplyActivity
 */
@WebServlet("/ApplyActivity")
public class ApplyActivity extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ApplyActivity() {
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
		System.out.println("111");
		ActivityControl con=new ActivityControl();
		ApplicationControl acon=new ApplicationControl();
		ErrorType type=new ErrorType();
		if(request.getParameter("num").equals("")) {
			JOptionPane.showMessageDialog(null," 学号不能为空");
			request.getRequestDispatcher("EnterActivity.jps").forward(request, response);
		}
		if(request.getParameter("name").equals("")) {
			JOptionPane.showMessageDialog(null," 用户名不能为空");
			request.getRequestDispatcher("EnterActivity.jps").forward(request, response);
		}
		int num=Integer.parseInt(request.getParameter("num"));
		int No=Integer.parseInt(request.getParameter("No"));
		Session sess = getSession();
		Student stuact=sess.get(Student.class, num);
		Activity act=sess.get(Activity.class, No);
		try {
			List<Activity> Activityall=con.loadActivities();
			 HttpSession session = request.getSession();
	            //把用户数据保存在session域对象中;

	        session.setAttribute("Activityall", Activityall);
			type=con.applyActivity(stuact, act);
			if(type.getErrorId()!=-1) {
				JOptionPane.showMessageDialog(null, new ErrorType().error[type.getErrorId()]);
				request.getRequestDispatcher("EnterActivity.jps").forward(request, response);}
			else {
				List<Application> ApplicationAllS=acon.loadApplication(stuact);
	            session.setAttribute("ApplicationAllS", ApplicationAllS);
				JOptionPane.showMessageDialog(null, "申请成功");
				request.getRequestDispatcher("homepage.html").forward(request, response);}
			
		} catch (BaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	}


