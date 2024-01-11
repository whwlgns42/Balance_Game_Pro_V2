package controller;

import java.io.IOException;
import java.util.Iterator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.user.Join;

@WebServlet("*.do")
public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public FrontController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("111");
		doAction(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("2222");
		doAction(request, response);
	}

	private void doAction(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String uri = request.getRequestURI();
		String cp = request.getContextPath();
		String action = uri.substring(cp.length());
		System.out.println(action);

		ActionForward forward = null;
		if (action.equals("/main.do")) { // 메인 페이지 이동 TODO 구현하기
			forward = new ActionForward();
			forward.setPath("main.jsp");
			forward.setRedirect(true);
		} else if (action.equals("/joinPage.do")) { // 회원가입 페이지 이동 TODO 구현하기
		} else if (action.equals("/join.do")) { // 회원가입 하기 TODO 구현하기
			forward = new Join().execute(request, response); // TODO 구현하기

		} else if (action.equals("/logout.do")) { // TODO 구현하기
		} else if (action.equals("/testPage.do")) { // TODO 구현하기

		} else if (action.equals("/mypage.do")) { // TODO 구현하기

		} else if (action.equals("/loginPage.do")) { // TODO 구현하기

		} else if (action.equals("/login.do")) { // TODO 구현하기

		} else if (action.equals("/replySelectOne.do")) { // TODO 구현하기

		} else if (action.equals("/replyInsert.do")) { // TODO 구현하기

		} else if (action.equals("/replyUpdate.do")) { // TODO 구현하기

		} else if (action.equals("/replyDelete.do")) { // TODO 구현하기

		} else if (action.equals("/changeName.do")) { // TODO 구현하기

		} else if (action.equals("/memberDelete.do")) {// TODO 구현하기

		} else if (action.equals("/alert.do")) { // TODO 구현하기
			forward = new ActionForward();
			forward.setPath("alert.jsp");
			forward.setRedirect(false);
		} else {

		}

		if (forward == null) { // TODO 구현하기
			// 에러 상황
		}

		if (forward.isRedirect()) {
			response.sendRedirect(forward.getPath());
		} else {
			RequestDispatcher dispatcher = request.getRequestDispatcher(forward.getPath());
			dispatcher.forward(request, response);
		}

	}

}
