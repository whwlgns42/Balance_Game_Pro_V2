package controller.page.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.common.Action;
import controller.common.ActionForward;
import model.question.QuestionDAO;
import model.question.QuestionDTO;

public class WishListDetailPageAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		ActionForward forward = new ActionForward();

		HttpSession session = request.getSession();
		String loginId = (String)session.getAttribute("loginId");
		
		QuestionDTO qDTO = new QuestionDTO();
		QuestionDAO qDAO = new QuestionDAO();

		int qId = Integer.parseInt(request.getParameter("qId"));
		
		
		qDTO.setqId(qId);
		qDTO.setLoginId(loginId);
		qDTO.setSearchCondition("문제상세조회");
		System.out.println("qId: " + qId);
		System.out.println("loginId" + loginId);
		qDTO = qDAO.selectOne(qDTO);
		System.out.println("qDTO: " + qDTO);
		if (qDTO == null) {
			forward.setPath("alert.do");
			forward.setRedirect(false);
			request.setAttribute("status", "fail");
			request.setAttribute("msg", "해당 데이터가 없습니다");
			request.setAttribute("redirect", "wishListPage.do");
			return forward;
		}
		forward.setPath("wishListDetail.jsp");
		forward.setRedirect(false);
		request.setAttribute("qDTO", qDTO);
		return forward;
	}

}
