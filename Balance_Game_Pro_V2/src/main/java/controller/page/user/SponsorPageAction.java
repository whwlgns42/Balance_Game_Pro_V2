package controller.page.user;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.common.Action;
import controller.common.ActionForward;
import model.support.SupportDAO;
import model.support.SupportDTO;



public class SponsorPageAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ActionForward forward = new ActionForward();

		SupportDTO supportDTO = new SupportDTO();
		SupportDAO supportDAO = new SupportDAO();
		
		supportDTO.setSearchCondition("후원목록");
		ArrayList<SupportDTO> supportDatas =  supportDAO.selectAll(supportDTO);
		if(supportDatas != null) {
			forward.setPath("sponsor.jsp");
			forward.setRedirect(false);
			request.setAttribute("datas", supportDatas);
		}else  {
			forward.setPath("sponsor.jsp");
			forward.setRedirect(false);
			request.setAttribute("datas", supportDatas);
		}
		return forward;
	}

}
