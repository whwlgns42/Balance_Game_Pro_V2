package controller.page.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.common.Action;
import controller.common.ActionForward;
import model.suggestion.SuggestionDAO;
import model.suggestion.SuggestionDTO;

public class UserSuggestionPageAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		ActionForward forward = new ActionForward();
		HttpSession session = request.getSession();
		String loginId = (String)session.getAttribute("loginId");
		
		SuggestionDTO sugDTO = new SuggestionDTO();
		
		request.setAttribute("loginId", loginId);
		
		forward.setPath("userSuggestion.jsp");
		forward.setRedirect(false);
		
		return forward;
	}

}
