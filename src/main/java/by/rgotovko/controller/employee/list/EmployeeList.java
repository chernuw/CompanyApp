package by.rgotovko.controller.employee.list;

import by.rgotovko.dao.DAOException;
import by.rgotovko.dao.DAOFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "EmployeeList", urlPatterns = "/EmployeeList")

public class EmployeeList extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int amountEmployees = DAOFactory.getEmployeeDAO().getAllEmployees().size();
            request.setAttribute("amountEmployees", amountEmployees);
            request.getRequestDispatcher("WEB-INF/pages/employees/list.jsp").forward(request,
                    response);
        } catch (DAOException e) {
            throw new ServletException(e);
        }

    }
}
