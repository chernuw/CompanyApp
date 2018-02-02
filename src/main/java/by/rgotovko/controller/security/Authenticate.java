package by.rgotovko.controller.security;

import by.rgotovko.dao.DAOException;
import by.rgotovko.dao.DAOFactory;
import by.rgotovko.entity.User;
import org.mindrot.jbcrypt.BCrypt;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "Authenticate", urlPatterns = "/Authenticate")
public class Authenticate extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String login = request.getParameter("login");
            String password = request.getParameter("password");
            User user = DAOFactory.getUserDAO().getUserByLogin(login);
            if(!BCrypt.checkpw(password, user.getPassword())){
                request.setAttribute("message", "Incorrect login or password");
                request.getRequestDispatcher("WEB-INF/pages/login.jsp").forward(request, response);
            } else {
                request.getSession().setAttribute("userLogin", user.getLogin());
                request.getSession().setAttribute("userId", user.getId());
                request.getSession().setAttribute("userRole", user.getRole());
                if(user.getRole().equals("ADMIN")){
                    response.sendRedirect("EmployeeList");
                }
                else{
                    response.sendRedirect("EmployeeProfile?id=" + user.getId());
                }
            }
        } catch (DAOException e) {
            throw new ServletException(e);
        }
    }
}
