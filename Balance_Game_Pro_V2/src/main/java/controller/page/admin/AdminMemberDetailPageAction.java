package controller.page.admin;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.common.Action;
import controller.common.ActionForward;
import model.comment.CommentDAO;
import model.comment.CommentDTO;
import model.member.MemberDAO;
import model.member.MemberDTO;

public class AdminMemberDetailPageAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		ActionForward forward = new ActionForward();
		MemberDTO mDTO = new MemberDTO();
		MemberDAO mDAO = new MemberDAO();
		CommentDTO cDTO = new CommentDTO();
		CommentDAO cDAO = new CommentDAO();
		
		mDTO.setLoginId(request.getParameter("loginId"));
		mDTO.setSearchCondition("유저조회");
		cDTO.setSearchCondition("유저댓글조회");
		cDTO.setLoginId(request.getParameter("loginId"));
		
		ArrayList<CommentDTO> cdatas = cDAO.selectAll(cDTO);
		
		MemberDTO member = mDAO.selectOne(mDTO);
		if (member == null) {
			forward.setPath("alert.do");
			request.setAttribute("status", "fail");
			request.setAttribute("fail", "회원이 존재하지 않습니다.");
			request.setAttribute("redirect", "adminPage.do");
			return forward;
	    } 
		
		forward.setPath("adminMemberDetail.jsp");
		forward.setRedirect(false);
		request.setAttribute("member", member);
		request.setAttribute("cdatas", cdatas);
		System.out.println("로그 cdatas[" + cdatas+"]");
		System.out.println("로그 member[" + member+"]");
	    return forward;
	}

}
