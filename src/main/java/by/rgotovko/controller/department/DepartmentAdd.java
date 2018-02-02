package by.rgotovko.controller.department;

import by.rgotovko.dao.DAOException;
import by.rgotovko.dao.DAOFactory;
import by.rgotovko.entity.Department;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "DepartmentAdd", urlPatterns = "/DepartmentAdd")
public class DepartmentAdd extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Department department = new Department();
            department.setName(request.getParameter("departmentName"));
            DAOFactory.getDepartmentDAO().addDepartment(department);
        } catch (DAOException e) {
            throw new ServletException(e);
        }
    }
}
