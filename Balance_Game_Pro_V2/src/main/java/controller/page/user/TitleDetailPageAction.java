package controller.page.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.common.Action;
import controller.common.ActionForward;
import model.question.QuestionDAO;
import model.question.QuestionDTO;

public class TitleDetailPageAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ActionForward forward = new ActionForward();
		QuestionDTO questionDTO = new QuestionDTO();
		QuestionDAO questionDAO = new QuestionDAO();

		questionDTO.setSearchCondition("문제상세조회");
		questionDTO.setWriter(request.getParameter("writer")); // 파라미터 : 작성자x, 로그인ID [조지훈]
		questionDTO.setqId(Integer.parseInt(request.getParameter("qid")));
		QuestionDTO questionData =  questionDAO.selectOne(questionDTO);
		if(questionData != null) {
			forward.setPath("titleDetail.jsp");
			forward.setRedirect(false);
			request.setAttribute("qData", questionData);
		}else {
			forward.setPath("titleDetail.jsp");
			forward.setRedirect(false);
			request.setAttribute("qData", questionData);
		}
		return forward ;
	}

}
