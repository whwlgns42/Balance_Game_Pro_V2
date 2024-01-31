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
		System.out.println(sugDatas+ "sugDatas <<<<<<<<");
		
		if(sugDatas.isEmpty()) {
			sugDatas = null;
			System.out.println("건의사항 널 뜬다");
		}
		System.out.println("건의사항 데이터는 있음");
		forward.setPath("adminMain.jsp");
		forward.setRedirect(false);
		request.setAttribute("sugDatas", sugDatas);
		return forward;
	}

}
