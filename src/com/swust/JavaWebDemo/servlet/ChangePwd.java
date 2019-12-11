package com.swust.JavaWebDemo.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import control.StudentControl;
import model.ErrorType;
import model.Student;
import util.BaseException;

/**
 * Servlet implementation class ChangePwd
 */
@WebServlet("/ChangePwd")
public class ChangePwd extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChangePwd() {
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
		System.out.println(111);
		ErrorType type=new ErrorType();
		int loginname=(int)request.getSession().getAttribute("loginName");
		String pwd = request.getParameter("pwd");
		String pwd2 = request.getParameter("pwd2");
		String old = request.getParameter("old");
		StudentControl con=new StudentControl();
		Session session = getSession();
		Student student=session.get(Student.class, loginname);
		try {
			type=con.ChangePwd(student,old, pwd, pwd2);
			if(type.getErrorId()!=-1) {
				JOptionPane.showMessageDialog(null, new ErrorType().error[type.getErrorId()]);
				request.getRequestDispatcher("PwdChange.jsp").forward(request, response);}
			else {
				JOptionPane.showMessageDialog(null, "申请成功");
				request.getRequestDispatcher("homepage.html").forward(request, response);}
			
		} catch (BaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
