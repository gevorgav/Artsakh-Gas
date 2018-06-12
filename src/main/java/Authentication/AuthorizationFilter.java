package Authentication;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


public class AuthorizationFilter implements Filter {
    FilterConfig filterConfig;
    public AuthorizationFilter() {
    }
//    @WebFilter(filterName = "AuthFilter", urlPatterns = { "*.xhtml" })

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        try {

            HttpServletRequest reqt = (HttpServletRequest) request;
            HttpServletResponse resp = (HttpServletResponse) response;
            HttpSession ses = reqt.getSession(false);
            String reqURI = reqt.getRequestURI();
            if((reqt != null && reqt.getParameterMap().get("username") != null && reqt.getParameterMap().get("username")[0].equals("admin") && reqt.getParameterMap().get("password") != null && reqt.getParameterMap().get("password")[0].equals("admin"))){
                resp.sendRedirect(reqt.getContextPath() + "/index.xhtml");

            }
            else if (reqURI.indexOf("/login.xhtml") >= 0
                    || reqURI.indexOf("/public/") >= 0
                    || reqURI.contains("javax.faces.resource")){
                chain.doFilter(request, response);
            }

            else
                resp.sendRedirect(reqt.getContextPath() + "/login.xhtml");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void destroy() {

    }

}