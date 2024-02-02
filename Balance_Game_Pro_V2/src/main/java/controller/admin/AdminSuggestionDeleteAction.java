package controller.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.common.Action;
import controller.common.ActionForward;
import model.suggestion.SuggestionDAO;
import model.suggestion.SuggestionDTO;

public class AdminSuggestionDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		ActionForward forward = new ActionForward();

		SuggestionDTO sugDTO = new SuggestionDTO();
		SuggestionDAO sugDAO = new SuggestionDAO();

		sugDTO.setSugId(Integer.parseInt(request.getParameter("sugId")));
		boolean flag = sugDAO.delete(sugDTO);
		if(!flag) {
			forward.setPath("alert.do");
			forward.setRedirect(false);
			request.setAttribute("status", "fail");
			request.setAttribute("msg", "실패했습니다");
			request.setAttribute("redirect", "adminSuggestionDetailPageAction.do");
			return forward;
		}
		forward.setPath("alert.do");
		forward.setRedirect(false);
		request.setAttribute("status", "success");
		request.setAttribute("msg", "삭제했습니다");
		request.setAttribute("redirect", "adminSuggestionDetailPageAction.do");
		return forward;
	}

}
