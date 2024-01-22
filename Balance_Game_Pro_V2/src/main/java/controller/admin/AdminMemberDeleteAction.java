package controller.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.common.Action;
import controller.common.ActionForward;
import model.member.MemberDAO;
import model.member.MemberDTO;

public class AdminMemberDeleteAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
 
		//한글코딩
		//뷰에서 보내준 회원 아이디를 셋팅해서 
		//모델의 멤버DAO delete로 보냄
		
		ActionForward forward = new ActionForward();
		MemberDTO mDTO =new MemberDTO();
		MemberDAO mDAO =new MemberDAO();
		mDTO.setLoginId(request.getParameter("loginId"));
		mDTO.setSearchCondition("회원삭제");
		System.out.println(request.getParameter("loginId"));
		
		boolean deleteResult = mDAO.delete(mDTO);
		System.out.println(mDAO.delete(mDTO)+"<<<<<");
		
		if(deleteResult) {
			System.out.println("삭제 성공");
			forward.setPath("adminMemberManagementPage.do");
			forward.setRedirect(true);
			return forward;
			
		}else {
			System.out.println("삭제 실패");
				forward = new ActionForward();
	            forward.setPath("alert.do");
	            forward.setRedirect(false);
	            request.setAttribute("status", "fail");
	            request.setAttribute("fail", "잘못된 요청입니다.");
	            request.setAttribute("redirect", "adminMemberManagementPage.do");
	            return forward;
		}
	}

}
