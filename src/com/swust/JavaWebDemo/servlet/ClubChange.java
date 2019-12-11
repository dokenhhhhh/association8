package com.swust.JavaWebDemo.servlet;

import java.io.IOException;
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

import control.ClubControl;
import control.StudentControl;
import model.Club;
import model.ErrorType;
import model.Student;
import util.BaseException;

/**
 * Servlet implementation class ClubChange
 */
@WebServlet("/ClubChange")
public class ClubChange extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ClubChange() {
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
		System.out.println(111);
		ClubControl con=new ClubControl();
		String name =new String(request.getParameter("name").getBytes("ISO-8859-1"),"utf-8");
		String com =request.getParameter("con");
		ErrorType type=new ErrorType();
		int loginname=(int)request.getSession().getAttribute("loginName");
		Session sess= getSession();
		Student student=sess.get(Student.class, loginname);
		 HttpSession session = request.getSession();
		try {
			Club club=con.getClubByStudent(student);
			type=con.changeClub(club, name, com);
			if(type.getErrorId()!=-1) {
				JOptionPane.showMessageDialog(null, new ErrorType().error[type.getErrorId()]);
				request.getRequestDispatcher("president/community_add.jsp").forward(request, response);}
			else {
				session.setAttribute("club", club);
				JOptionPane.showMessageDialog(null, "修改成功");
				request.getRequestDispatcher("president/community_add.jsp").forward(request, response);}
		} catch (BaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
