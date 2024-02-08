package controller.page.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.common.Action;
import controller.common.ActionForward;

public class UserSuggestionPageAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		ActionForward forward = new ActionForward();
		HttpSession session = request.getSession();
		String loginId = (String)session.getAttribute("loginId");
				
		request.setAttribute("loginId", loginId);
		
		forward.setPath("userSuggestion.jsp");
		forward.setRedirect(false);
		
		return forward;
	}

}
