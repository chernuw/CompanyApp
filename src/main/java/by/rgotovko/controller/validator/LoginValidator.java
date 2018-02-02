package by.rgotovko.controller.validator;

import by.rgotovko.dao.DAOException;
import by.rgotovko.dao.DAOFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "LoginValidator", urlPatterns = "/LoginValidator")
public class LoginValidator extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException {
        try {
            String login = request.getParameter("login");
            Integer empId = Integer.parseInt(request.getParameter("empId"));
            boolean valid = DAOFactory.getUserDAO().isValidValue("login", login, empId);
            response.setContentType("application/json");
            response.getWriter().write("{\"valid\":" + valid + "}");
        } catch (DAOException e) {
            throw new ServletException(e);
        }
    }
}
