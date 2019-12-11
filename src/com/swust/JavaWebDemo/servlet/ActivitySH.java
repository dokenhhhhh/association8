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
import model.Activity;
import model.Admin;
import model.Application;
import model.Club;
import model.ErrorType;
import util.BaseException;

/**
 * Servlet implementation class ActivitySH
 */
@WebServlet("/ActivitySH")
public class ActivitySH extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ActivitySH() {
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
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("进入了post方法");
		String name = request.getParameter("ope");
		int id = Integer.parseInt(request.getParameter("id"));
		ClubControl clubcontrol=new ClubControl();
		ApplicationControl con =new ApplicationControl();
		 HttpSession session = request.getSession();
         //把用户数据保存在session域对象中
		 ErrorType type=new ErrorType();
		 Club club=new Club();
		 System.out.println(name);
		 try {	
			 Application app=new Application();
			 
			 	Session sess = getSession();
			 	app=sess.get(Application.class, id);
			 	
			 	club=clubcontrol.getClubByName(name);
				type=clubcontrol.approveClub(club);
				if(type.getErrorId()!=-1) {
					JOptionPane.showMessageDialog(null, new ErrorType().error[type.getErrorId()]);
					request.getRequestDispatcher("admin/ApplyClubs.jsp").forward(request, response);}
			else{
				con.ChangeApplicationStation(app, "审核通过");
				List<Application> ApplicationAll = con.loadApplicationForAdmain1();
				List<Club> ClubAll =clubcontrol.loadClub();
				session.setAttribute("ClubAll", ClubAll);
				session.setAttribute("ApplicationAll", ApplicationAll);
				System.out.println("输入正确");
				//正确，则进入另一个页面s
				JOptionPane.showMessageDialog(null, "申请成功");
				request.getRequestDispatcher("admin/ApplyClubs.jsp").forward(request, response); 
			}
		} catch (BaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		 }
}
