package controller.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.common.Action;
import controller.common.ActionForward;
import model.question.QuestionDAO;
import model.question.QuestionDTO;

public class AdminTitleAccessAcion implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		ActionForward forward = new ActionForward();
		forward.setPath("adminTitleAccessPage.do");
		forward.setRedirect(true);
		
		QuestionDTO qDTO = new QuestionDTO();
		QuestionDAO qDAO = new QuestionDAO();
		HttpSession session = request.getSession();
		
		qDTO.setTitle(request.getParameter("title"));
		qDTO.setAnswer_A(request.getParameter("answer_A"));
		qDTO.setAnswer_B(request.getParameter("answer_B"));
		qDTO.setWriter((String)session.getAttribute("member"));
		qDAO.insert(qDTO);
		
		
		return forward;
	}

}
