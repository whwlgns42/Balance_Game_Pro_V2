package controller.page.admin;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.common.Action;
import controller.common.ActionForward;
import model.support.SupportDAO;
import model.support.SupportDTO;

public class AdminSupportPageAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		ActionForward forward = new ActionForward();
		
		SupportDAO sDAO = new SupportDAO();
		SupportDTO sDTO = new SupportDTO();
		
		sDTO.setSearchCondition("후원목록");
		ArrayList<SupportDTO> sdatas = sDAO.selectAll(sDTO);
		
		sDTO.setSearchCondition("총후원금액");
		SupportDTO totalAmount = sDAO.selectOne(sDTO);
		
		if(sdatas == null) {
			forward.setPath("alert.do");
			forward.setRedirect(false);
			request.setAttribute("status", "fail");
			request.setAttribute("msg", "해당 데이터가 없습니다");
			request.setAttribute("redirect", "adminPage.do");
			return forward;
		}
		request.setAttribute("sdatas", sdatas);
		request.setAttribute("totalAmount", totalAmount);
		forward.setPath("adminSponsor.jsp");
		forward.setRedirect(false);
		return forward;
	}

}
