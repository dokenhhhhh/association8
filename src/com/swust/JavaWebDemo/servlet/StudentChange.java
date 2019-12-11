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
import org.hibernate.cfg.Configuration;

import control.StudentControl;
import model.ErrorType;
import model.Student;
import util.BaseException;

/**
 * Servlet implementation class StudentChange
 */
@WebServlet("/StudentChange")
public class StudentChange extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StudentChange() {
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
		System.out.println(111);
		StudentControl con =new StudentControl();
		ErrorType type=new ErrorType();
		int loginname=(int)request.getSession().getAttribute("loginName");
		String usename=request.getParameter("usename");
		String sex=request.getParameter("sex");
		String major =request.getParameter("major");
		String cl =request.getParameter("class");
		String tel = request.getParameter("tel");
		Session session = getSession();
		Student student=session.get(Student.class, loginname);
		request.setAttribute("student", student);
		try {
			type=con.ChangeStudent(student, usename, sex, major, cl, tel);
			if(type.getErrorId()!=-1) {
				JOptionPane.showMessageDialog(null, new ErrorType().error[type.getErrorId()]);
				request.getRequestDispatcher("personalmessage.jsp").forward(request, response);}
			else {
				JOptionPane.showMessageDialog(null, "修改成功");
				request.getRequestDispatcher("personalmessage.jsp").forward(request, response);}
		} catch (BaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
