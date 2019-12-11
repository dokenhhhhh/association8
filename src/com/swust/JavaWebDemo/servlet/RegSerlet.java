package com.swust.JavaWebDemo.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;

import control.AdminControl;
import control.StudentControl;
import model.Admin;
import model.ErrorType;
import model.Student;
import util.BaseException;

/**
 * Servlet implementation class RegSerlet
 */
@WebServlet("/RegSerlet")
public class RegSerlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegSerlet() {
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
		//判断是否正确
		System.out.println("进入了post方法");
		Student student =new Student();
		StudentControl con=new StudentControl();
		if(request.getParameter("username").equals("")) {
			JOptionPane.showMessageDialog(null," 用户名不能为空");
			request.getRequestDispatcher("reg.html").forward(request, response);
		}
		int username = Integer.parseInt(request.getParameter("username"));
		String pass = request.getParameter("pass");
		String  pwd2=request.getParameter("pwd2");
		ErrorType type=new ErrorType(); 
		try {
			type=con.addStudent(username, pass, pwd2);
			if(type.getErrorId()!=-1) {
				JOptionPane.showMessageDialog(null, new ErrorType().error[type.getErrorId()]);
				request.getRequestDispatcher("reg.html").forward(request, response);}
			else {
				JOptionPane.showMessageDialog(null, "注册成功");
				request.getRequestDispatcher("login.html").forward(request, response);}
			}	
		 catch (BaseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}

}
