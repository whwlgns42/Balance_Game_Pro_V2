package controller.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.common.Action;
import controller.common.ActionForward;
import model.question.QuestionDAO;
import model.question.QuestionDTO;

public class AdminTitleRefuseAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		//한글코딩
		//모델에서 사용자가 올리는 순간 기본값은 false로 승인되지 않게 해놈
		//그럼 거절이유를 적어서 사용자에게 보내주어야함 
		//이 거절을 하는 action이 필요가 있나?
		
		ActionForward forward = new ActionForward();
		
		QuestionDTO qDTO = new QuestionDTO();
		QuestionDAO qDAO = new QuestionDAO();
		
		qDTO.setqId(Integer.parseInt(request.getParameter("qid")));
		boolean flag = qDAO.delete(qDTO);
		if(!flag) {
			forward.setPath("alert.do");
			forward.setRedirect(false);
			request.setAttribute("status", "fail");
			request.setAttribute("msg", "실패했습니다");
			request.setAttribute("redirect", "adminTitleManagementPage.do");
			return forward;
		}
		forward.setPath("alert.do");
		forward.setRedirect(false);
		request.setAttribute("status", "success");
		request.setAttribute("msg", "거절했습니다");
		request.setAttribute("redirect", "adminTitleManagementPage.do");
		return forward;
	}

}
