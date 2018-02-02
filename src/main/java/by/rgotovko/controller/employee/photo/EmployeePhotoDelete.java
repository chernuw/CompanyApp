package by.rgotovko.controller.employee.photo;

import by.rgotovko.dao.DAOException;
import by.rgotovko.dao.DAOFactory;
import by.rgotovko.entity.Employee;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "EmployeePhotoDelete", urlPatterns = "/EmployeePhotoDelete")
public class EmployeePhotoDelete extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Integer idEmployee = Integer.valueOf(request.getParameter("idEmployee"));
            Employee employee = DAOFactory.getEmployeeDAO().getEmployeeById(idEmployee);
            employee.setPhoto(null);
            DAOFactory.getEmployeeDAO().updateEmployee(employee);
        } catch (DAOException e) {
            throw new ServletException(e);
        }
    }
}
