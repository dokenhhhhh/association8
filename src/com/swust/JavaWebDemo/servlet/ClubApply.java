package com.swust.JavaWebDemo.servlet;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import control.ClubControl;
import control.MemberControl;
import model.Club;
import model.ErrorType;
import model.Student;
import util.BaseException;

/**
 * Servlet implementation class ClubApply
 */
@WebServlet("/ClubApply")
public class ClubApply extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ClubApply() {
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
		System.out.println("111");
		ClubControl con=new ClubControl();
		ErrorType type=new ErrorType();
		int loginname=(int)request.getSession().getAttribute("loginName");
		System.out.println(loginname);
		String name = request.getParameter("clubname");
		String clubcontent = request.getParameter("clubcontent");
		Session sess=getSession();
		MemberControl mcon= new MemberControl();
		try {
			type=con.addClub(loginname, name, clubcontent);
			if(type.getErrorId()!=-1) {
				JOptionPane.showMessageDialog(null, new ErrorType().error[type.getErrorId()]);
				request.getRequestDispatcher("clubapply.jsp").forward(request, response);}
			else {

//				mcon.addMember(club.getClubId(),"",loginname,"","");
				JOptionPane.showMessageDialog(null, "申请成功");
				request.getRequestDispatcher("homepage.html").forward(request, response);}
			
		} catch (BaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
