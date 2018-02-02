package by.rgotovko.controller.security;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "RoleFilter", urlPatterns = {"/EmployeeList", "/EmployeeProfile"})
public class RoleFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        if (!(servletRequest instanceof HttpServletRequest)) {
            filterChain.doFilter(servletRequest, servletResponse);
        }
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;
        String queryString = httpRequest.getQueryString();
        HttpSession session = httpRequest.getSession();
        String id = "";
        if(httpRequest.getRequestURI().endsWith("EmployeeProfile")){
            id = queryString.substring(queryString.lastIndexOf("id=") + 3);
        }
        if (session.getAttribute("userRole").toString().equals("ADMIN")
                || (session.getAttribute("userId").toString().equals(id))) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            httpResponse.sendRedirect("AccessDenied");
        }
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }


    @Override
    public void destroy() {

    }
}
