package controller.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.common.Action;
import controller.common.ActionForward;
import model.member.MemberDAO;
import model.member.MemberDTO;

public class ResignAction implements Action { // 회원탈퇴 기능

	// 손성용
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		request.setCharacterEncoding("UTF-8");

		ActionForward forward = new ActionForward();
		
		// 전달된 정보 저장
		MemberDTO memberDTO = new MemberDTO();
		String memberLoginId = request.getParameter("loginId");
		
		// 회원탈퇴 클릭시 로그 찍기
		// System.out.println(memberLoginId + "회원탈퇴 버튼 눌렀을대 넘어오는 아이디값");
		
		// 오브젝트 => String 으로 강제 형 변환
		// 공식 : String 지정변수 = (String)session.getAttribute("LoginId") => (LoginId 를 세션에서
		// 가져 온다)

		// 세션 아이디 들고옴.
		// 엠디티오에 세션 아이디값을 넣어준다
		memberDTO.setLoginId(memberLoginId);

		// DB 연결해서 삭제
		MemberDAO memberDAO = new MemberDAO();
		System.out.println("mDTO : " + memberDTO);
		boolean result = memberDAO.delete(memberDTO); // MemberDAO == DELETE 연결
		
		// 회원탈퇴 여부 로그 찍기
		// System.out.println(result + "회원탈퇴 성공 여부");

		if (result) {
			// 탈퇴가 완료되면 세션 초기화 및 메인으로 이동
			
			// 삭제 여부 확인 로그 찍기
			// System.out.println("삭제됨"); 
			
			forward.setPath("alert.do");
			forward.setRedirect(false);
			request.setAttribute("status", "success");
			request.setAttribute("msg", "회원탈퇴가 완료되었습니다");
			request.setAttribute("redirect", "main.do");
			session.invalidate();

		} else {
			forward.setPath("alert.do");
			forward.setRedirect(false);
			request.setAttribute("status", "fail");
			request.setAttribute("msg", "회원탈퇴가 취소되었습니다");
			request.setAttribute("redirect", "main.do");

		}

		return forward;
	}
}