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

import control.ActivityControl;
import control.ApplicationControl;
import control.MemberControl;
import model.Activity;
import model.Admin;
import model.Application;
import model.ErrorType;
import model.Student;
import util.BaseException;

/**
 * Servlet implementation class GetUserName
 */
@WebServlet("/CreateActivity")
public class CreateActivity extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateActivity() {
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
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		ActivityControl con =new ActivityControl();
		ErrorType type=new ErrorType();
		String aname=new String(request.getParameter("aname").getBytes("ISO-8859-1"),"utf-8");
		String id=request.getParameter("id");
		String No =request.getParameter("No");
		String time =request.getParameter("time");
		String about = request.getParameter("about");
		Admin act=(Admin)request.getSession().getAttribute("admin");
		ApplicationControl con1 =new ApplicationControl();
		 HttpSession session = request.getSession();
         //把用户数据保存在session域对象中
		try {
			type=con.addActivity(act,aname,No, id,time, about);
			if(type.getErrorId()!=-1) {
				JOptionPane.showMessageDialog(null, new ErrorType().error[type.getErrorId()]);
				request.getRequestDispatcher("president/manager_update.jsp").forward(request, response);}
			else {
				JOptionPane.showMessageDialog(null, "申请成功");
				List<Application> ApplicationAll = con1.loadApplicationForAdmain();
				session.setAttribute("ApplicationAll", ApplicationAll);
				List<Activity> ActivityAll = con.loadActivities();
				request.setAttribute("ActivityAll", ActivityAll);
				System.out.println("输入正确");
				//正确，则进入另一个页面
				request.getRequestDispatcher("president/manager_update.jsp").forward(request, response);}
		} catch (BaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}	


