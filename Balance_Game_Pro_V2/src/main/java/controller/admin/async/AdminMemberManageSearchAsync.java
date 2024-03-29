package controller.admin.async;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import model.member.MemberDAO;
import model.member.MemberDTO;

@WebServlet("/adminMemberManageSearchAsync")
public class AdminMemberManageSearchAsync extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AdminMemberManageSearchAsync() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		
		PrintWriter out = response.getWriter();

		Gson gson = new Gson();
		MemberDTO mDTO = new MemberDTO();
		MemberDAO mDAO = new MemberDAO();
		mDTO.setLoginId(request.getParameter("searchData"));
		System.out.println(request.getParameter("searchData"));
		mDTO.setSearchCondition("아이디조회");
		ArrayList<MemberDTO> mdatas = mDAO.selectAll(mDTO);
		String memberData = gson.toJson(mdatas);
//		System.out.println("주소 " + mdatas.get(0).getAddress());
	
		if(memberData != null) {
			System.out.println("유저관리 데이터" + memberData);
			out.print(memberData);
		}
		else {
			out.print(gson.toJson(memberData));
		}
	}
}
