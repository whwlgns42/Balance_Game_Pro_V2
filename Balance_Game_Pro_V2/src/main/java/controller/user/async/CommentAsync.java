package controller.user.async;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import model.comment.CommentDAO;
import model.comment.CommentDTO;



@WebServlet("/commentAsync.do")
public class CommentAsync extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public CommentAsync() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		
		CommentDAO cDAO = new CommentDAO();
		CommentDTO cDTO = new CommentDTO();
		cDTO.setqId(Integer.parseInt(request.getParameter("qId")));
		cDTO.setSearchCondition("질문댓글조회");
		ArrayList<CommentDTO> datas=cDAO.selectAll(cDTO);
		
		Gson gson=new Gson();
		String json =gson.toJson(datas);
		System.out.println(json);
		if (datas.isEmpty()) {
			out.print("실패");
		}else {
			out.print(json);
		}
		



	}

}
