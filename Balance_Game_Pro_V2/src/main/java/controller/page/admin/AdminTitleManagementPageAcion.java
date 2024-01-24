package controller.page.admin;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.common.Action;
import controller.common.ActionForward;
import model.question.QuestionDAO;
import model.question.QuestionDTO;

public class AdminTitleManagementPageAcion implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ActionForward forward = new ActionForward();
		
		QuestionDTO qDTO = new QuestionDTO();
		QuestionDAO qDAO = new QuestionDAO();
		qDTO.setSearchCondition("관리자문제전체조회");
		ArrayList<QuestionDTO> qdatas_t = qDAO.selectAll(qDTO);
		System.out.println("qdatas_t" + qdatas_t);
		if(qdatas_t == null) {
			forward.setPath("alert.do");
			forward.setRedirect(false);
			request.setAttribute("status", "fail");
			request.setAttribute("msg", "해당 데이터가 없습니다");
			request.setAttribute("redirect", "adminPage.do");
			return forward;
		}
		forward.setPath("adminTitleManagement.jsp");
		forward.setRedirect(false);
		request.setAttribute("qdatas_t", qdatas_t);
		System.out.println("qdatas_t" + qdatas_t);
		
		return forward;
	}

}
