 package controller.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.common.Action;
import controller.common.ActionForward;
import model.question.QuestionDAO;
import model.question.QuestionDTO;

public class AdminTitleUpdateAcion implements Action{

   @Override
   public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
         throws ServletException, IOException {

      ActionForward forward = new ActionForward();
      
      QuestionDTO qDTO = new QuestionDTO();
      QuestionDAO qDAO = new QuestionDAO();
      
      qDTO.setSearchCondition("문제수정");
      qDTO.setTitle(request.getParameter("title"));
      qDTO.setAnswer_A(request.getParameter("answer_A"));
      qDTO.setAnswer_B(request.getParameter("answer_B"));
      qDTO.setExplanation(request.getParameter("explanation"));
      qDTO.setCategory(Integer.parseInt(request.getParameter("category")));
      qDTO.setqAccess(request.getParameter("qAccess"));
      boolean flag = qDAO.update(qDTO);
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
      request.setAttribute("msg", "수정되었습니다");
      request.setAttribute("redirect", "adminTitleManagementPage.do");
      return forward;
   }

}