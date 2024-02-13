package controller.admin.async;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import model.suggestion.SuggestionDAO;
import model.suggestion.SuggestionDTO;

@WebServlet("/idSearchAsync")
public class IdSearchAsync extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public IdSearchAsync() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doAction(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doAction(request, response);
	}

	private void doAction(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		SuggestionDTO suggestionDTO = new SuggestionDTO();
		SuggestionDAO suggestionDAO = new SuggestionDAO();
		suggestionDTO.setLoginId(request.getParameter("searchData"));
		System.out.println(request.getParameter("searchData") + " <<< 검색 파라미터");
		suggestionDTO.setSearchCondition("아이디조회");
//		if(request.getParameter("searchNameMode").equals("true")) { // 최종 추가
//			suggestionDTO.setSearchCondition("이름조회"); // 최종 추가
//		}
		ArrayList<SuggestionDTO> sugDatas = suggestionDAO.selectAll(suggestionDTO);
		Gson gson = new Gson();
		response.setContentType("application/json;charset=UTF-8");
		if (sugDatas != null) {
			System.out.println(sugDatas);
			System.out.println("건의사항 데이터 있음"); // 조지훈
			String jsonData = gson.toJson(sugDatas);
			response.getWriter().print(jsonData);
		}
		else {
			System.out.println("건의사항 데이터 없음"); // 조지훈
			String jsonData = gson.toJson(sugDatas);
			response.getWriter().print(jsonData);
		}
	}

}
