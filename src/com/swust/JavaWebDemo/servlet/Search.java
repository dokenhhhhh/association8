package com.swust.JavaWebDemo.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Member;
import model.Student;
import control.ActivityControl;
import control.ApplicationControl;
import control.ClubControl;
import control.MemberControl;
import model.Activity;
import model.Application;
import model.Club;
import util.BaseException;

/**
 * Servlet implementation class Search
 */
@WebServlet("/Search")
public class Search extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Search() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// 获取界面传来的参数
				String search = request.getParameter("search");
				// 判断是要查询那个
				if (search.equals("activity")) {//查询member的所有信息 返回集合
					String membername = new String(request.getParameter("name").getBytes("ISO-8859-1"),"utf-8");
				
					ActivityControl memberdao = new ActivityControl();
					List<Activity> ActivityAll;
					try {
						ActivityAll = memberdao.loadActivityByKey(membername);
						request.setAttribute("ActivityAll", ActivityAll);
					} catch (BaseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					
					//返回到成员列表的界面
					request.getRequestDispatcher("admin/ActivityList.jsp").forward(request, response);
				} else if (search.equals("club")) {//查询community的所有信息 返回集合
					String comname = request.getParameter("name");
					ClubControl ccon=new ClubControl();
					List<Club> ClubAll;
					try {
						ClubAll = ccon.loadClubByKey(comname);
						request.setAttribute("ClubAll", ClubAll);
					} catch (BaseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					//返回到社团列表的界面
					request.getRequestDispatcher("admin/community_list.jsp").forward(request, response);
				}
				else if (search.equals("member")) {//查询community的所有信息 返回集合
					String mname = request.getParameter("name");
					MemberControl mcon=new MemberControl();
					List<Member> MemberAll;
					try {
						Club c=(Club)request.getSession().getAttribute("club");
						MemberAll = mcon.getMemberById(mname,c);
						request.setAttribute("MemberAll", MemberAll);
					} catch (BaseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					//返回到社团列表的界面
					request.getRequestDispatcher("president/member_list.jsp").forward(request, response);
				}
				else if (search.equals("enclub")) {//查询community的所有信息 返回集合
					String enname = request.getParameter("name");
					ApplicationControl acon=new ApplicationControl();
					List<Application> ApplicationEnterClub;
					try {
						Student s=(Student)request.getSession().getAttribute("student");
						ApplicationEnterClub = acon.loadApplicationForProprieterByKey(s, enname);
						request.setAttribute("ApplicationEnterClub", ApplicationEnterClub);
					} catch (BaseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					//返回到社团列表的界面
					request.getRequestDispatcher("president/ApplyClubs.jsp").forward(request, response);
				}
				else if (search.equals("crclub")) {//查询community的所有信息 返回集合
					String crname = request.getParameter("name");
					ApplicationControl acon=new ApplicationControl();
					List<Application> ApplicationAll;
					try {
						Student s=(Student)request.getSession().getAttribute("student");
						ApplicationAll = acon.loadClubApplicationForAdmainBykey(crname);
						request.setAttribute("ApplicationAll", ApplicationAll);
					} catch (BaseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					//返回到社团列表的界面
					request.getRequestDispatcher("admin/ApplyClubs.jsp").forward(request, response);
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
