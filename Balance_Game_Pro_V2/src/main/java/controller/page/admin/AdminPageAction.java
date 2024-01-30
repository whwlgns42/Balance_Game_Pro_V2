package controller.page.admin;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.common.Action;
import controller.common.ActionForward;
import model.suggestion.SuggestionDAO;
import model.suggestion.SuggestionDTO;

public class AdminPageAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ActionForward forward = new ActionForward();
		
		SuggestionDTO sugDTO = new SuggestionDTO();
		SuggestionDAO sugDAO = new SuggestionDAO();
		
		ArrayList<SuggestionDTO> sugDatas = sugDAO.selectAll(sugDTO);
		
		if(sugDatas.isEmpty()) {
			sugDatas = null;
		}
		forward.setPath("adminMain.jsp");
		forward.setRedirect(false);
		request.setAttribute("sugDatas", sugDatas);
		return forward;
	}

}
