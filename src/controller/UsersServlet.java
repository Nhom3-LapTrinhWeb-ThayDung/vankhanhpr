package controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Time;
import java.util.List;
import java.util.Timer;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import dao.User_infoDAO;
import dao.UsersDAO;
import model.User_info;
import model.Users;

/**
 * Servlet implementation class UsersServlet
 */
@WebServlet("/UsersServlet")
public class UsersServlet extends HttpServlet {

	UsersDAO usersDAO = new UsersDAO();
	User_infoDAO user_infoDAO = new User_infoDAO();
	private static final long serialVersionUID = 1L;


	
	
	
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UsersServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String command = request.getParameter("command");
		String url = "";
		Users users = new Users();
		User_info user_info = new User_info();
		HttpSession session = request.getSession();
		switch(command){
		case "insert":
			long x = new java.util.Date().getTime();
			user_info.setId(x);
			user_info.setEmail(request.getParameter("email-register"));
			user_info.setGioitinh(Integer.parseInt(request.getParameter("gioitinh")));
			user_info.setNgaysinh(request.getParameter("namsinh")
					+"-"+ request.getParameter("thangsinh")
					+"-"+ request.getParameter("ngaysinh"));
			user_info.setTen(request.getParameter("name"));
			user_info.setSodienthoai(request.getParameter("sdt"));
			boolean f = user_infoDAO.insertUser_info(user_info);
			if(f)
			{
				users.setUserName(request.getParameter("username-register"));
				users.setUserPass(request.getParameter("pass-register"));
				users.setUserEmail(request.getParameter("email-register"));
				users.setUserID(x);
				f = usersDAO.insertUser(users);
				if(f)
				{
					session.setAttribute("user_info", user_info);
					session.setAttribute("user", users);
					url="index.jsp";
				}
				
			}
			else
			{
				//session.removeAttribute("user");
				url="index.jsp";
				response.getWriter().write("Error user name or password or position");
			}
			/*RequestDispatcher rd = request.getRequestDispatcher(url);
			rd.forward(request, response);*/
			break;

		case "login":
			users = usersDAO.login(request.getParameter("username"),request.getParameter("password") );
			user_info = user_infoDAO.login(request.getParameter("username"), request.getParameter("password"),Integer.parseInt(request.getParameter("quyen")));
			if(users!=null &&  user_info!=null){
				session.setAttribute("user", users);
				session.setAttribute("user_info", user_info);
				url = "index.jsp";
			}
			else{
				//request.setAttribute("error","Error user name or password");
				response.getWriter().write("Error user name or password or position");
			}
			/*rd = request.getRequestDispatcher(url);
			rd.forward(request, response);*/
			request.setAttribute("url", url);
			break;
			
		case "doimk":
			String errorStr = "";
			String user_name = request.getParameter("user_name");
			String newpass1 = request.getParameter("newpass1");
			f = usersDAO.doimk(user_name, newpass1);
			if(f)
			{
				response.getWriter().write("Đổi mật khẩu thành công!");
				//errorStr="Ä�á»•i máº­t kháº©u thĂ nh cĂ´ng";
				///request.setAttribute("errorStr", errorStr);
			}
			else
			{
				response.getWriter().write("Đổi mật khẩu không thành công!");
				//errorStr="Ä�á»•i máº­t kháº©u khĂ´ng thĂ nh cĂ´ng";
				//request.setAttribute("errorStr", errorStr);
			}
			Users u= (Users)session.getAttribute("user");
			u.setUserPass(newpass1);
			session.setAttribute("user", u);
			break;
			
		case "update":
			User_info u2= (User_info)session.getAttribute("user_info");
			User_info u1= new User_info();
			u1.setId(u2.getId());
			u1.setQuyen(u2.getQuyen());
			u1.setAnhdaidien(u2.getAnhdaidien());
			u1.setEmail(request.getParameter("email"));
			u1.setGioitinh(Integer.parseInt(request.getParameter("gioitinh")));
			u1.setNgaysinh(request.getParameter("namsinh")
					+"-"+ request.getParameter("thangsinh")
					+"-"+ request.getParameter("ngaysinh"));
			u1.setTen(request.getParameter("ten"));
			u1.setSodienthoai(request.getParameter("sodienthoai"));
			u1.setDiachi(request.getParameter("diachi"));
			f = user_infoDAO.updateUser_info(u1);
			if(f)
			{
					
					response.getWriter().write("update success!");
			}
			else
			{
				//session.removeAttribute("user");
				response.getWriter().write("update unsuccessful!");
			}
			break;
		case "updateuser":
			User_info u4= new User_info();
			u4.setId(Long.parseLong(request.getParameter("user_id")));
			u4.setQuyen(Integer.parseInt(request.getParameter("user_quyen")));
			u4.setEmail(request.getParameter("email"));
			u4.setGioitinh(Integer.parseInt(request.getParameter("gioitinh")));
			u4.setAnhdaidien(request.getParameter("anhdaidien"));
			u4.setNgaysinh(request.getParameter("namsinh"));
			u4.setTen(request.getParameter("ten"));
			u4.setSodienthoai(request.getParameter("sodienthoai"));
			u4.setDiachi(request.getParameter("diachi"));
			f = user_infoDAO.updateUser_info(u4);
			if(f)
			{
				
				response.getWriter().write("update success!");
			}
			else
			{
			//session.removeAttribute("user");
				response.getWriter().write("update unsuccessful!");
			}
			break;
		}
		
		
	}

}
