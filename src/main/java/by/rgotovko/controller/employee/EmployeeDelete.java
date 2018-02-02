package by.rgotovko.controller.employee;

import by.rgotovko.dao.DAOException;
import by.rgotovko.dao.DAOFactory;
import by.rgotovko.entity.Employee;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "EmployeeDelete", urlPatterns = "/EmployeeDelete")
public class EmployeeDelete extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException {
        try{
            Integer oldEmployee = Integer.parseInt(request.getParameter("idEmployee"));
            Employee employee = DAOFactory.getEmployeeDAO().getEmployeeById(oldEmployee);
            employee.setDepartment(null);
            employee.setPosition(null);
            DAOFactory.getEmployeeDAO().deleteEmployee(employee);
        } catch (DAOException e) {
            throw new ServletException(e);
        }
    }
}
