package controller.page.user;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.common.Action;
import controller.common.ActionForward;
import model.save.SaveDAO;
import model.save.SaveDTO;

public class WishListPageAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ActionForward forward = new ActionForward();
		
		SaveDTO sDTO = new SaveDTO();
		SaveDAO sDAO = new SaveDAO();
		
		sDTO.setLoginId(request.getParameter("loginId"));
		ArrayList<SaveDTO> datas = sDAO.selectAll(sDTO);
		
		if(datas == null) {
			forward.setPath("alert.do");
			forward.setRedirect(false);
			request.setAttribute("status", "fail");
			request.setAttribute("msg", "해당 데이터가 없습니다");
			request.setAttribute("redirect", "main.do");
			return forward;
		}
		forward.setPath("wishList.jsp");
		forward.setRedirect(false);
		request.setAttribute("sdatas", datas);
		return forward;
	}

}
