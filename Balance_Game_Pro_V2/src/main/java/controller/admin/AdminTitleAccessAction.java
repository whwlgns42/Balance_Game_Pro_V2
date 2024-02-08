package controller.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.common.Action;
import controller.common.ActionForward;
import model.question.QuestionDAO;
import model.question.QuestionDTO;

public class AdminTitleAccessAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//문제승인 
		ActionForward forward = new ActionForward();

		QuestionDTO qDTO = new QuestionDTO();
		QuestionDAO qDAO = new QuestionDAO();
		qDTO.setSearchCondition("승인");
		qDTO.setqId(Integer.parseInt(request.getParameter("qid")));
		qDTO.setTitle(request.getParameter("title"));
		qDTO.setAnswer_A(request.getParameter("answer_A"));
		qDTO.setAnswer_B(request.getParameter("answer_B"));
		qDTO.setCategory(Integer.parseInt(request.getParameter("category")));
		qDTO.setExplanation(request.getParameter("Explanation"));

		boolean flag = qDAO.update(qDTO);
		if(!flag) {
			forward.setPath("alert.do");
			forward.setRedirect(false);
			request.setAttribute("status", "fail");
			request.setAttribute("msg", "실패했습니다");
			request.setAttribute("redirect", "adminTitleAccessPage.do");
			return forward;
		}
		forward.setPath("alert.do");
		forward.setRedirect(false);
		request.setAttribute("status", "success");
		request.setAttribute("msg", "성공했습니다");
		request.setAttribute("redirect", "adminTitleAccessPage.do");
		return forward;
	}

}