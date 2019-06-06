package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import utils.PageUtil;
import beans.Page;
import beans.User;
import dao.UserDao;
import daoImpl.UserDaoImpl;

public class UserServlet extends HttpServlet{

	private UserDao userDao = new UserDaoImpl();
	private Page page = new Page();

	@Override
	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//request.setCharacterEncoding("utf-8"); 
		String method = request.getParameter("method");
		if("login".equals(method)){
			login(request,response);
		}else if("regist".equals(method)){
			regist(request,response);
		}else if("queryAll".equals(method)){
			queryAll(request, response);
		}else if("queryByUserName".equals(method)){
			queryByUserName(request, response);
		}else if("updateUserPassword".equals(method)){
			updateUserPassword(request, response);
		}else if("deleteById".equals(method)){
			deleteById(request, response);
		}
	}
	
	public void login(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String userName = request.getParameter("userName");
		//System.out.println("userName=="+userName);
		String password = request.getParameter("password");
		response.setContentType("text/html;charset=utf-8");
		User user = userDao.login(userName, password);
		if(user != null){
			request.getSession().setAttribute("user",user);
			//request.setAttribute("userName", userName);
			request.getRequestDispatcher("loginSuccess.jsp").forward(request, response);
		}else{
			response.getWriter().print("µÇÂ¼Ê§°Ü£¬ÇëÖØÐÂµÇÂ¼£¡");
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}
	}
	
	public void regist(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		response.setContentType("text/html;charset=utf-8");
			if(userDao.regist(userName, password)){
				request.setAttribute("msg", "×¢²á³É¹¦");
				request.getRequestDispatcher("login.jsp").forward(request, response);
		}else{
			request.setAttribute("msg","×¢²áÊ§°Ü");
			request.getRequestDispatcher("regist.jsp").forward(request, response);
		}
		
	}
	
	public void queryAll(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/*List<User> userList = userDao.queryAll(page);
		request.setAttribute("userList", userList);
		request.getRequestDispatcher("queryAll.jsp").forward(request, response);*/
		
		int rowCount = userDao.rowCount();
		String pageIndexPre = request.getParameter("pageIndex");
		int pageIndex = 0;
		if(pageIndexPre == null){
			pageIndex = 1;
		}else{
			pageIndex = Integer.parseInt(pageIndexPre);
		}
		Page page = PageUtil.getPage(PageUtil.PAGESIZE, pageIndex, rowCount);
		List<User> userList = userDao.queryAll(page);
		request.setAttribute("userList", userList);
		request.setAttribute("page", page);
		request.getRequestDispatcher("queryAll.jsp").forward(request, response);
	}
	
	public void queryByUserName(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String userName = request.getParameter("userName");
		//userName=new String(userName.getBytes("iso8859-1"), "utf-8");
		response.setContentType("text/html;charset=utf-8");
		List<User> userList = userDao.queryByUserName(userName);
		request.setAttribute("userList", userList);
		request.getRequestDispatcher("queryByUserName.jsp").forward(request, response);
	}
	
	public void updateUserPassword(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		response.setContentType("text/html;charset=utf-8");
		if(userDao.updateUserPassword(userName, password)){
			request.setAttribute("msg", "ÐÞ¸Ä³É¹¦");
			queryAll(request, response);
			//request.getRequestDispatcher("queryAll.jsp").forward(request, response);
		}else{
			request.setAttribute("msg", "ÐÞ¸ÄÊ§°Ü");
			queryAll(request, response);
			//request.getRequestDispatcher("queryAll.jsp").forward(request, response);
		}
	}
	
	public void deleteById(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		if(userDao.deleteById(id)){
			request.setAttribute("msg", "É¾³ý³É¹¦");
			queryAll(request, response);
			//response.getWriter().print(1);
			//request.getRequestDispatcher("queryAll.jsp").forward(request, response);
		}else{
			request.setAttribute("msg", "É¾³ýÊ§°Ü");
			queryAll(request, response);
			//request.getRequestDispatcher("queryAll.jsp").forward(request, response);
		}
		
	}
}
