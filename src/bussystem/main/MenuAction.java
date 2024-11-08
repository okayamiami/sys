package bussystem.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tool.Action;

public class MenuAction extends Action{

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
//
//		HttpSession session = req.getSession(true);
//
//		String user = (String)session.getAttribute("user");
//		req.setAttribute("user", user);
//
//		System.out.println(user);
//
		req.getRequestDispatcher("menu.jsp").forward(req, res);

	}
}