package controller.page.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.common.Action;
import controller.common.ActionForward;
import model.save.SaveDAO;
import model.save.SaveDTO;

public class WishListDetailPageAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		ActionForward forward = new ActionForward();
		
		SaveDTO sDTO = new SaveDTO();
		SaveDAO sDAO = new SaveDAO();
		
		sDTO.setsId(Integer.parseInt(request.getParameter("sid")));
		System.out.println("sid: " + request.getParameter("sid"));
		sDTO = sDAO.selectOne(sDTO);
		System.out.println("sDTO: "+sDTO);
		if(sDTO == null) {
			forward.setPath("alert.do");
			forward.setRedirect(false);
			request.setAttribute("status", "fail");
			request.setAttribute("msg", "해당 데이터가 없습니다");
			request.setAttribute("redirect", "wishListPage.do");
			return forward;
		}
		forward.setPath("wishListDetail.jsp");
		forward.setRedirect(false);
		request.setAttribute("sDTO", sDTO);
		return forward;
	}

}
