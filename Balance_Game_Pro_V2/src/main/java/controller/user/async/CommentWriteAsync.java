package controller.user.async;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import model.comment.CommentDAO;
import model.comment.CommentDTO;
import model.member.MemberDAO;
import model.member.MemberDTO;


@WebServlet("/commentWriteAsync")
public class CommentWriteAsync extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public CommentWriteAsync() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		CommentDAO cDAO = new CommentDAO();
		CommentDTO cDTO = new CommentDTO();
		
				
		
		cDTO.setqId(Integer.parseInt(request.getParameter("qId")));
		cDTO.setLoginId(request.getParameter("loginId"));
		cDTO.setContent(request.getParameter("comment"));
		cDTO.setSearchCondition("댓글생성");
		cDAO.insert(cDTO);
		
		MemberDAO mDAO=new MemberDAO();
		MemberDTO mDTO =new MemberDTO();
		mDTO.setSearchCondition("유저조회");
		mDTO.setLoginId(request.getParameter("loginId"));
		mDTO= mDAO.selectOne(mDTO);
		
		cDTO.setMemberName(mDTO.getName());
		cDTO.setGrade(mDTO.getGrade());
		
		Gson gson=new Gson();
		String json =gson.toJson(cDTO);
		System.out.println(json);
		
		out.print(json);
		
	}

}
