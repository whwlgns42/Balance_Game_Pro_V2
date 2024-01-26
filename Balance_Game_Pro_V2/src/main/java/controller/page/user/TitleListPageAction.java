package controller.page.user;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.common.Action;
import controller.common.ActionForward;
import model.question.QuestionDAO;
import model.question.QuestionDTO;

public class TitleListPageAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("11111");
		ActionForward forward = new ActionForward();
		
		QuestionDTO questionDTO = new QuestionDTO();
		QuestionDAO questionDAO = new QuestionDAO();
		questionDTO.setSearchCondition("문제전체조회");
		ArrayList<QuestionDTO> datas = questionDAO.selectAll(questionDTO);
		System.out.println("222");
		System.out.println(datas);
		if(datas != null) {
			forward.setPath("titleList.jsp");
			forward.setRedirect(false);
			request.setAttribute("qDatas", datas);
		}else {
			forward.setPath("alert.do");
			forward.setRedirect(false);
			request.setAttribute("status", "success");
			request.setAttribute("msg", "등록된 문제가 없습니다.");
			request.setAttribute("redirect", "main.do");
		}
		return forward;
	}

}
