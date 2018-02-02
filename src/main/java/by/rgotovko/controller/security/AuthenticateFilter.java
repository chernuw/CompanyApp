package by.rgotovko.controller.security;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "AuthenticateFilter", urlPatterns = "/*")
public class AuthenticateFilter implements Filter {

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        if (!(req instanceof HttpServletRequest)) {
            chain.doFilter(req, resp);
        }
        HttpServletRequest httpRequest = (HttpServletRequest) req;
        HttpServletResponse httpResponse = (HttpServletResponse) resp;
        String uri = httpRequest.getRequestURI();
        HttpSession session = httpRequest.getSession();
        if (session.getAttribute("userLogin") != null || uri.matches(".+\\..+")
                || uri.endsWith("Authenticate") || uri.endsWith("Login")) {
            chain.doFilter(req, resp);
        } else {
            httpResponse.sendRedirect("Login");
        }

    }

    public void init(FilterConfig config) throws ServletException {

    }

    public void destroy() {
    }

}
