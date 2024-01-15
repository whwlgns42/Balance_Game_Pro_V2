package controller.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.common.Action;
import controller.common.ActionForward;
import model.question.QuestionDAO;
import model.question.QuestionDTO;

public class MakeTitleAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ActionForward forward = new ActionForward();
		request.setCharacterEncoding("UTF-8");

		QuestionDTO questionDTO = new QuestionDTO();
		QuestionDAO questionDAO = new QuestionDAO();
		HttpSession session = request.getSession();
		questionDTO.setSearchCondition("문제생성");
		questionDTO.setWriter((String) session.getAttribute("loginId")); // 로그인 아이디
		questionDTO.setTitle(request.getParameter("title")); // 문제 제목
		questionDTO.setAnswer_A(request.getParameter("answer_A")); // 문제 답변A
		questionDTO.setAnswer_B(request.getParameter("answer_B")); // 문제 답변A
		questionDTO.setExplanation(request.getParameter("explanation")); // 문제 설명
		boolean questionData = questionDAO.insert(questionDTO);

		if (questionData) {
			// 문제 출제 하기 성공
			forward.setPath("alert.do");
			forward.setRedirect(false);
			request.setAttribute("status", "success");
			request.setAttribute("msg", "문제 등록에 성공하였습니다.");
			request.setAttribute("redirect", "main.do");
		} else {
			forward.setPath("alert.do");
			forward.setRedirect(false);
			request.setAttribute("status", "success");
			request.setAttribute("msg", "문제 등록에 실패하였습니다. 다시 입력해주세요");
			request.setAttribute("redirect", "makeTitlePage.do");
		}
		return forward;
	}

}
