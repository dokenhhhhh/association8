package com.swust.JavaWebDemo.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;

import org.hibernate.Session;

import control.AdminControl;
import control.StudentControl;
import model.Admin;
import model.ErrorType;
import model.Student;
import util.BaseException;

/**
 * Servlet implementation class ChangeAdmin
 */
@WebServlet("/ChangeAdmin")
public class ChangeAdmin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChangeAdmin() {
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
		System.out.println("111");
		ErrorType type=new ErrorType();
		Admin admin=(Admin)request.getSession().getAttribute("admin");
		String pwd = request.getParameter("newpass");
		String pwd2 = request.getParameter("newpass2");
		String old = request.getParameter("userpass");
		AdminControl con=new AdminControl();
		try {
			type=con.changePwd(admin,old, pwd, pwd2);
			if(type.getErrorId()!=-1) {
				JOptionPane.showMessageDialog(null, new ErrorType().error[type.getErrorId()]);
				request.getRequestDispatcher("admin/manager_update.jsp").forward(request, response);
				}
			else {
				JOptionPane.showMessageDialog(null, "申请成功");
				request.getRequestDispatcher("admin/manager_update.jsp").forward(request, response);}
			
		} catch (BaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
