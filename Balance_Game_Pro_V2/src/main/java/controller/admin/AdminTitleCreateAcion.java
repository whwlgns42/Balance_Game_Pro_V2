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

public class AdminTitleCreateAcion implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//문제생성
		ActionForward forward = new ActionForward();
		forward.setPath("adminTitleManagementPage.do");
		forward.setRedirect(false);
		//관리자페이지에서 문제를 작성
		//뷰에서 문제를 작성하면 parameter값으로 받아옴 
		//해당 DTO에 set함 
		//작성한 것을 모델의 insert함
		
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
