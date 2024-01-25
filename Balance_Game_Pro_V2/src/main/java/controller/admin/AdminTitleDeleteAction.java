package controller.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.common.Action;
import controller.common.ActionForward;
import model.question.QuestionDAO;
import model.question.QuestionDTO;

public class AdminTitleDeleteAcion implements Action{

   @Override
   public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
         throws ServletException, IOException {

      ActionForward forward = new ActionForward();
      
      QuestionDTO qDTO = new QuestionDTO();
      QuestionDAO qDAO = new QuestionDAO();

      qDTO.setqId(Integer.parseInt(request.getParameter("qid")));
      boolean flag = qDAO.delete(qDTO);
      if(!flag) {
         forward.setPath("alert.do");
         forward.setRedirect(false);
         request.setAttribute("status", "fail");
         request.setAttribute("msg", "실패했습니다");
         request.setAttribute("redirect", "adminTitleManagementPage.do");
         return forward;
      }
      forward.setPath("alert.do");
      forward.setRedirect(false);
      request.setAttribute("status", "success");
      request.setAttribute("msg", "문제를 삭제했습니다");
      request.setAttribute("redirect", "adminTitleManagementPage.do");
      return forward;
   }

}