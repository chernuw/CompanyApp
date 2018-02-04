package by.rgotovko.controller.validator;

import by.rgotovko.dao.DAOException;
import by.rgotovko.dao.DAOFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "EmailValidator", urlPatterns = "/EmailValidator")
public class EmailValidator extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException {
        try {
            String email = request.getParameter("email");
            int empId;
            if (!request.getParameter("empId").equals("")) {
                empId = Integer.parseInt(request.getParameter("empId"));
            } else {
                empId = 0;
            }
            boolean valid = DAOFactory.getEmployeeDAO().isValidValue("email", email, empId);
            response.setContentType("application/json");
            response.getWriter().write("{\"valid\":" + valid + "}");
        } catch (DAOException e) {
            throw new ServletException(e);
        }
    }
}
