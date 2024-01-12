package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.admin.AdminMemberDeleteActionAcion;
import controller.admin.AdminTitleAccessAcion;
import controller.admin.AdminTitleCreateAcion;
import controller.admin.AdminTitleDeleteAcion;
import controller.admin.AdminTitleRefuseAcion;
import controller.admin.AdminTitleUpdateAcion;
import controller.common.ActionForward;
import controller.page.AdminMemberDetailPageAction;
import controller.page.AdminMemberManagementPageAcion;
import controller.page.AdminPageAcion;
import controller.page.AdminTitleAccessPageAcion;
import controller.page.AdminTitleDetailPageAcion;
import controller.page.AdminTitleDetaileAccessPageAcion;
import controller.page.AdminTitleManagementPageAcion;
import controller.page.GamePageAction;
import controller.page.MakeTitlePageAction;
import controller.page.PwCheckPageAcion;
import controller.page.ResultPageAction;
import controller.page.SponsorPageAction;
import controller.page.TitleListPageAction;
import controller.page.WishListPageAction;
import controller.user.GameAction;
import controller.user.JoinAction;
import controller.user.MakeTitleAction;
import controller.user.MyPageUpdateAction;
import controller.user.PwCheckAcion;
import controller.user.ResultAction;
import controller.user.SmsCheckAction;
import controller.user.SponsorAction;
import controller.user.WriteCommentAction;

@WebServlet("*.do")
public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public FrontController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doAction(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
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
			forward = new ActionForward();
			forward.setPath("join.jsp");
			forward.setRedirect(true);
		} else if (action.equals("/join.do")) { // 회원가입 하기 TODO 구현하기
			forward = new JoinAction().execute(request, response); // TODO 구현하기

		} else if (action.equals("/wish.do")) { // 찜하기 기능

		} else if (action.equals("/wishListPage.do")) { // 찜하기 페이지 이동
			forward = new WishListPageAction().execute(request, response);
		} else if (action.equals("/makeTitle.do")) { // 문제 출제 기능
			forward = new MakeTitleAction().execute(request, response);
		} else if (action.equals("/makeTitlePage.do")) { // 문제 출제 페이지 이동 
			forward = new MakeTitlePageAction().execute(request, response);
		} else if (action.equals("/result.do")) { // 게임결과 기능
			forward = new ResultAction().execute(request, response);
		} else if (action.equals("/resultPage.do")) { // 게임 결과 페이지 이동
			forward = new ResultPageAction().execute(request, response);
		} else if (action.equals("/writeComment.do")) { // 댓글작성 기능
			forward = new WriteCommentAction().execute(request, response);
		} else if (action.equals("/titleListPage.do")) { // 댓글 작성 페이지 이동.
			forward = new TitleListPageAction().execute(request, response);
		} else if (action.equals("/mypageUpdate.do")) { // 내정보 수정하기 기능 (이름 / 이메일)
			forward = new MyPageUpdateAction().execute(request, response);
		} else if (action.equals("/pwCheck.do")) { // 2차 비밀번호 체크 기능 
			forward = new PwCheckAcion().execute(request, response);
		} else if (action.equals("/pwCheckPage.do")) { // 2차 비밀번호 체크 페이지 이동
			forward = new PwCheckPageAcion().execute(request, response);
		} else if (action.equals("/game.do")) { // 게임하기 기능
			forward = new GameAction().execute(request, response);
		} else if (action.equals("/gamePage.do")) { // 게임하기 페이지 이동
			forward = new GamePageAction().execute(request, response);
		} else if (action.equals("/sponsorPage.do")) { // 후원하기 페이지 이동
			forward = new SponsorPageAction().execute(request, response);
		} else if (action.equals("/sponsor.do")) { // 후원하기 기능
			forward = new SponsorAction().execute(request, response);
		} else if (action.equals("/smsCheck.do")) { // 문자인증번호 발송 기능 (비동기)
			forward = new SmsCheckAction().execute(request, response);
		} else if (action.equals("/adminPage.do")) { // 관리자 메인 페이지 이동 -- 관리자 관련 페이지 이동 (o)
			forward = new AdminPageAcion().execute(request, response);

		} else if (action.equals("/adminTitleManagementPage.do")) { // 관리자 문제관리페이지 가기  (o)
			forward = new AdminTitleManagementPageAcion().execute(request, response); 

		} else if (action.equals("/adminTitleDetailPage.do")) { // 관리자 문제 상세페이지 가기 (o)
			forward = new AdminTitleDetailPageAcion().execute(request, response);

		} else if (action.equals("/adminMemberManagementPage.do")) { // 관리자 유저관리페이지 가기 (o)
			forward = new AdminMemberManagementPageAcion().execute(request, response);

		} else if (action.equals("/adminMemberManagementPage.do")) { // 관리자 유저상세페이지 가기 (o)
			forward = new AdminMemberDetailPageAction().execute(request, response);
		} 
		else if (action.equals("/adminTitleDetaileAccessPage.do")) { // 관리자 문제승인상세페이지 가기 (o)
			forward = new AdminTitleDetaileAccessPageAcion().execute(request, response);

		} else if (action.equals("/adminTitleAccessPage.do")) { // 관리자 문제승인페이지 가기 (o)
			forward = new AdminTitleAccessPageAcion().execute(request, response);

		} else if (action.equals("/adminTitleAccess.do")) { // 문제승인하기 -- 관리자 관련 기능 액션
			forward = new AdminTitleAccessAcion().execute(request, response);

		} else if (action.equals("/adminTitleRefuse.do")) { // 문제거절하기
			forward = new AdminTitleRefuseAcion().execute(request, response);

		} else if (action.equals("/adminTitleCreate.do")) { // 문제만들기
			forward = new AdminTitleCreateAcion().execute(request, response);

		} else if (action.equals("/adminTitleDelete.do")) { // 문제삭제하기
			forward = new AdminTitleDeleteAcion().execute(request, response);

		} else if (action.equals("/adminTitleUpdate.do")) { // 문제수정하기
			forward = new AdminTitleUpdateAcion().execute(request, response);

		} else if (action.equals("/adminMemberDelete.do")) { // 멤버삭제하기
			forward = new AdminMemberDeleteActionAcion().execute(request, response);

		} else if (action.equals("/alert.do")) { // TODO 알림창 구현하기
			forward = new ActionForward();
			forward.setPath("alert.jsp");
			forward.setRedirect(false);
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
