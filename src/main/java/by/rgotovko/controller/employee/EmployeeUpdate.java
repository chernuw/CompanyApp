package by.rgotovko.controller.employee;

import by.rgotovko.dao.DAOException;
import by.rgotovko.dao.DAOFactory;
import by.rgotovko.dao.EmployeeDAO;
import by.rgotovko.entity.Department;
import by.rgotovko.entity.Employee;
import by.rgotovko.entity.Position;
import by.rgotovko.entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;

@WebServlet(name = "EmployeeUpdate", urlPatterns = "/EmployeeUpdate")
public class EmployeeUpdate extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            EmployeeDAO employeeDAO = DAOFactory.getEmployeeDAO();
            Employee employee = employeeDAO.getEmployeeById(Integer.parseInt(request.getParameter("employeeId")));
            fillEmployee(request, employee);
            employee.getUser().setRole(request.getParameter("login"));
            if(request.getSession().getAttribute("userRole").equals("ADMIN")){
                employee.setDepartment(getDepartment(request));
                employee.setPosition(getPosition(request));
                employee.setHireDate(LocalDate.parse(request.getParameter("hireDate")));
                employee.getUser().setRole(request.getParameter("role"));
            }
            employeeDAO.updateEmployee(employee);
            response.sendRedirect("EmployeeProfile?id=" + employee.getId());
        } catch (DAOException e) {
            throw new ServletException(e);
        }
    }

    private void fillEmployee(HttpServletRequest request, Employee employee) {
        employee.setFirstName(request.getParameter("firstName"));
        employee.setMiddleName(request.getParameter("middleName"));
        employee.setLastName(request.getParameter("lastName"));
        employee.setBirthDate(LocalDate.parse(request.getParameter("birthDate")));
        employee.setEmail(request.getParameter("email"));
        employee.setPhone(request.getParameter("phone"));
        employee.setAddress(request.getParameter("address"));
    }

    private Department getDepartment(HttpServletRequest request) throws ServletException {
        try {
            return DAOFactory.getDepartmentDAO()
                    .getDepartmentByName(request.getParameter("department"));
        } catch (DAOException e) {
            throw new ServletException(e);
        }
    }

    private Position getPosition(HttpServletRequest request) throws ServletException {
        try {
            return DAOFactory.getPositionDAO()
                    .getPositionByTitle(request.getParameter("position"));
        } catch (DAOException e) {
            throw new ServletException(e);
        }
    }
}
