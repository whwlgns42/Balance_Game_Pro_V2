package controller.page.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.common.Action;
import controller.common.ActionForward;
import model.suggestion.SuggestionDAO;
import model.suggestion.SuggestionDTO;

public class AdminSuggestionDetailPageAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		ActionForward forward = new ActionForward();
		
		SuggestionDTO sugDTO = new SuggestionDTO();
		SuggestionDAO sugDAO = new SuggestionDAO();
		
		sugDTO.setSugId(Integer.parseInt(request.getParameter("sugId")));
		sugDTO = sugDAO.selectOne(sugDTO);
		
		if(sugDTO == null) {
			forward.setPath("alert.do");
			forward.setRedirect(false);
			request.setAttribute("status", "fail");
			request.setAttribute("msg", "해당 데이터가 없습니다");
			request.setAttribute("redirect", "adminSuggestionDetailPageAction.jsp");
			return forward;
		}
		forward.setPath("adminSuggestionDetailPageAction.jsp");
		forward.setRedirect(false);
		request.setAttribute("sugDTO", sugDTO);
		return forward;
	}

}
