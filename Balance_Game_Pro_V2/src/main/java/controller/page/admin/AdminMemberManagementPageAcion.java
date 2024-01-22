package controller.page.admin;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.common.Action;
import controller.common.ActionForward;
import model.member.MemberDAO;
import model.member.MemberDTO;

public class AdminMemberManagementPageAcion implements Action {
//
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		ActionForward forward = new ActionForward();
		MemberDTO memberDTO = new MemberDTO();
		MemberDAO memberDAO = new MemberDAO();
		memberDTO.setSearchCondition("전체조회");
		ArrayList<MemberDTO> members = memberDAO.selectAll(memberDTO);
		if (members != null) {
			System.out.println("데이터 있음");
			request.setAttribute("member", members);
			forward.setPath("adminMemberManagement.jsp");
			forward.setRedirect(false);
		} else {
			System.out.println("데이터 없음");
			forward.setPath("alert.do");
			forward.setRedirect(false);
			request.setAttribute("status", "fail");
			request.setAttribute("fail", "회원이 존재하지 않습니다.");
			request.setAttribute("redirect", "adminPage.do");
		}
		return forward;
	}

}
