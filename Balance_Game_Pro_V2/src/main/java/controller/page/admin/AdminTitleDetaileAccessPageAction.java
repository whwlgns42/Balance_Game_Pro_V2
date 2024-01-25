package controller.page.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.common.Action;
import controller.common.ActionForward;
import model.question.QuestionDAO;
import model.question.QuestionDTO;

public class AdminTitleDetaileAccessPageAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ActionForward forward = new ActionForward();
		
		QuestionDTO qDTO = new QuestionDTO();
		QuestionDAO qDAO = new QuestionDAO();
		int qid = Integer.parseInt(request.getParameter("qid"));
		qDTO.setqId(qid);
		qDTO.setSearchCondition("관리자문제상세조회");
		qDTO = qDAO.selectOne(qDTO);
		
		if(qDTO == null) {
			forward.setPath("alret.do");
			forward.setRedirect(false);
			request.setAttribute("status", "fail");
			request.setAttribute("msg", "해당 데이터가 없습니다");
			request.setAttribute("redirect", "adminTitleAccessPage.do");
			return forward;
		}
		forward.setPath("adminTitleDetailAccess.jsp");
		forward.setRedirect(false);
		request.setAttribute("qDTO", qDTO);
		return forward;
	}

}
