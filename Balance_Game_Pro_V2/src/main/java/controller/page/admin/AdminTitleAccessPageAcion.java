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

public class AdminTitleAccessPageAcion implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ActionForward forward = new ActionForward();
		
		QuestionDTO qDTO = new QuestionDTO();
		QuestionDAO qDAO = new QuestionDAO();
		qDTO.setSearchCondition("관리자승인문제조회");
		ArrayList<QuestionDTO> qdatas_f = qDAO.selectAll(qDTO);
		System.out.println("qdatas_f"+qdatas_f);
		if(qdatas_f == null) {
			forward.setPath("alert.do");
			forward.setRedirect(false);
			request.setAttribute("status", "fail");
			request.setAttribute("msg", "해당 데이터가 없습니다");
			request.setAttribute("redirect", "adminPage.do");
			return forward;
		}
		forward.setPath("adminTitleAccess.jsp");
		forward.setRedirect(false);
		request.setAttribute("qdatas_f", qdatas_f);
		return forward;
	}
}
