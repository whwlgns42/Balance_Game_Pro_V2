package controller.page;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.common.Action;
import controller.common.ActionForward;

public class AdminMemberManagementPageAcion implements Action{
//
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		 
		ActionForward forward = new ActionForward();
		forward.setPath("adminMemberManagement.jsp");
		forward.setRedirect(true);
		return forward;
	}

}
