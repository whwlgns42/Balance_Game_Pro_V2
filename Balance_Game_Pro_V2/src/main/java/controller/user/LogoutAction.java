package controller.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.common.Action;
import controller.common.ActionForward;

public class LogoutAction implements Action{
	
	// 손성용
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		ActionForward forward = new ActionForward();
		forward.setPath("alert.do");
		forward.setRedirect(false);
		
		request.setCharacterEncoding("UTF-8");
		
		HttpSession session = request.getSession();
		session.removeAttribute("loginId");
		
		request.setAttribute("msg", "로그아웃 완료");
		request.setAttribute("status", "success");
		request.setAttribute("redirect", "main.do");
		
		
		
		return forward;
	}
}
