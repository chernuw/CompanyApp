package by.rgotovko.controller.employee.list;

import by.rgotovko.dao.DAOException;
import by.rgotovko.dao.DAOFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "EmployeesAmount", urlPatterns = "/EmployeesAmount")
public class EmployeesAmount extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int amount = DAOFactory.getEmployeeDAO().getEmployeesAmount();
            response.setContentType("application/json");
            response.getWriter().write("{\"amount\":" + amount + "}");
        } catch (DAOException e) {
            throw new ServletException(e);
        }
    }
}
