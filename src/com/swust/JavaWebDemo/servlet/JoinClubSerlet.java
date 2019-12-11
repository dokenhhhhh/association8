package com.swust.JavaWebDemo.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;

import control.ClubControl;
import control.MemberControl;
import model.Club;
import model.ErrorType;
import util.BaseException;

/**
 * Servlet implementation class JoinClubSerlet
 */
@WebServlet("/JoinClubSerlet")
public class JoinClubSerlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public JoinClubSerlet() {
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
		System.out.println(111);
		MemberControl con =new MemberControl();
		ClubControl con1 =new ClubControl();
		ErrorType type=new ErrorType();
		if(request.getParameter("num").equals("")) {
			JOptionPane.showMessageDialog(null," 用户名不能为空");
			request.getRequestDispatcher("joinclub.jsp").forward(request, response);
		}
		int num = Integer.parseInt(request.getParameter("num"));
		String name = request.getParameter("name");
		String No = request.getParameter("No");
		String tel = request.getParameter("tel");
		String about = request.getParameter("about");
		try {
			Club club=con1.getClubByName(No);
			type=con.addMember(club.getClubId(), name, num, tel,about);
			if(type.getErrorId()!=-1) {
				JOptionPane.showMessageDialog(null, new ErrorType().error[type.getErrorId()]);
				request.getRequestDispatcher("joinclub.jsp").forward(request, response);}
			else {
				JOptionPane.showMessageDialog(null, "申请成功");
				request.getRequestDispatcher("club.html").forward(request, response);}
		} catch (BaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
