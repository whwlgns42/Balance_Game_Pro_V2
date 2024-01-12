package controller.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.common.Action;
import controller.common.ActionForward;
import model.question.QuestionDAO;
import model.question.QuestionDTO;

public class AdminTitleUpdateAcion implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		ActionForward forward = new ActionForward();
		forward.setPath("adminTitleManagementPage.do");
		forward.setRedirect(true);
		
		QuestionDTO qDTO = new QuestionDTO();
		QuestionDAO qDAO = new QuestionDAO();
		
		qDTO.setTitle(request.getParameter("title"));
		qDTO.setAnswer_A(request.getParameter("answer_A"));
		qDTO.setAnswer_B(request.getParameter("answer_B"));
		qDAO.update(qDTO);
		
		return forward;
	}

}
