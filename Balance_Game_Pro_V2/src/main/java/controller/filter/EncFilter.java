package controller.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;

@WebFilter({ "*.do", "*.jsp" })
public class EncFilter extends HttpFilter implements Filter {
       
	private String encoding; 
	
    public EncFilter() {
        super();
   }

	public void destroy() {	
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		if(request.getCharacterEncoding() == null) {
			request.setCharacterEncoding(encoding); // 하드코딩 : 유지보수 용이성을 망가뜨리는 주범ㅠ
		}
		System.out.println(" [로그] 필터 서블릿 클래스에서 작성한 로그");
		
		chain.doFilter(request, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {
		this.encoding=fConfig.getServletContext().getInitParameter("encoding");
	}

}



















