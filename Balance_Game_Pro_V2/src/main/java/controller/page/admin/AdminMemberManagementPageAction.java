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

public class AdminMemberManagementPageAction implements Action {
//
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		ActionForward forward = new ActionForward();
		MemberDTO mDTO = new MemberDTO();
		MemberDAO mDAO = new MemberDAO();
		mDTO.setSearchCondition("전체조회");
		ArrayList<MemberDTO> mdatas = mDAO.selectAll(mDTO);
		System.out.println("member" + mdatas);
		if (mdatas == null) {
			forward.setPath("alert.do");
			forward.setRedirect(false);
			request.setAttribute("status", "fail");
			request.setAttribute("fail", "회원이 존재하지 않습니다.");
			request.setAttribute("redirect", "adminPage.do");
			return forward;
		}
		forward.setPath("adminMemberManagement.jsp");
		request.setAttribute("member", mdatas);
		forward.setRedirect(false);
		return forward;
	}

}
