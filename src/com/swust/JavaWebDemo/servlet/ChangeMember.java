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
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import control.AdminControl;
import control.ClubControl;
import control.MemberControl;
import model.Admin;
import model.Club;
import model.ErrorType;
import model.Member;
import model.Student;
import util.BaseException;

/**
 * Servlet implementation class ChangeMember
 */
@WebServlet("/ChangeMember")
public class ChangeMember extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChangeMember() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
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
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("111");
		ErrorType type=new ErrorType();
		MemberControl mcon=new MemberControl();
		ClubControl con=new ClubControl();
		String id1  = request.getParameter("id");
		 HttpSession session = request.getSession();
		 Student stu=(Student)request.getSession().getAttribute("student");
		try {
			int id=Integer.parseInt(id1);
			Session sess = getSession();
			Transaction transaction = sess.beginTransaction();
			Member result=(Member)sess.createQuery("from model.Member where memberNum = "+id+"").uniqueResult();
			type=mcon.changeMember(result);
			Member mem=mcon.getMemberByName(stu.getStudentId());
			System.out.println(mem.getMemberNum());
			mem.setPost("社员");
			System.out.println(mem.getPost());
			sess.merge(mem);
			transaction.commit();
			sess.close();
			if(type.getErrorId()!=-1) {
				JOptionPane.showMessageDialog(null, new ErrorType().error[type.getErrorId()]);
				request.getRequestDispatcher("president/community_list.jsp").forward(request, response);
				}
			else {
				Club club=con.getClubByStudent(stu);
				List<Member> MemberAll=mcon.loadMember(club);
				session.setAttribute("MemberAll", MemberAll);
				JOptionPane.showMessageDialog(null, "申请成功");
				request.getRequestDispatcher("president/community_list.jsp").forward(request, response);}
			
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
