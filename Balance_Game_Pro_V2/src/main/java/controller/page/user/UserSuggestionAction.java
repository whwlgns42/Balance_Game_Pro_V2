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

public class UserSuggestionAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		ActionForward forward = new ActionForward();
		
		SuggestionDTO sugDTO = new SuggestionDTO();
		SuggestionDAO sugDAO = new SuggestionDAO();
		HttpSession session = request.getSession();
		String loginId = (String)session.getAttribute("loginId");
		
		sugDTO.setLoginId(loginId);
		sugDTO.setSuggestion(request.getParameter("suggestion"));
		
		boolean flag = sugDAO.insert(sugDTO);
		
		if(!flag) {
			forward.setPath("alert.do");
			forward.setRedirect(false);
			request.setAttribute("status", "fail");
			request.setAttribute("msg", "전송에 실패했습니다");
			request.setAttribute("redirect", "main.do");
		}
		forward.setPath("UserSuggestionPageAction.do");
		forward.setRedirect(true);
		
		return forward;
	}
}
