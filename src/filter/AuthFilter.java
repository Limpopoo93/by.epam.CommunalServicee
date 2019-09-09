package filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "AuthFilter",
        urlPatterns = {"/*"})
public class AuthFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        HttpSession session = request.getSession(false);
        String loginURI = request.getContextPath() + "/userLoginServlet";
        String loginPAGE = request.getContextPath()+ "/userAddServlet";

        boolean loggedIn = (session != null) && (session.getAttribute("user") != null);
        boolean loginRequest = request.getRequestURI().equals(loginURI);
        boolean logins = request.getRequestURI().equals(loginPAGE);
        if (loggedIn || loginRequest || logins) {
            chain.doFilter(request, response);
        } else {
            if(loggedIn == true){

                response.sendRedirect(loginPAGE);
            }else{
                response.sendRedirect(loginURI);
            }

        }
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
