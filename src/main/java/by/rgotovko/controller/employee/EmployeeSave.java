package by.rgotovko.controller.employee;

import by.rgotovko.dao.DAOException;
import by.rgotovko.dao.DAOFactory;
import by.rgotovko.dao.EmployeeDAO;
import by.rgotovko.dao.UserDAO;
import by.rgotovko.entity.Department;
import by.rgotovko.entity.Employee;
import by.rgotovko.entity.Position;
import by.rgotovko.entity.User;
import org.mindrot.jbcrypt.BCrypt;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;

@WebServlet(name = "EmployeeSave", urlPatterns = "/EmployeeSave")
public class EmployeeSave extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            UserDAO userDAO = DAOFactory.getUserDAO();
            User user;
            Employee employee;
            if (request.getParameter("employeeId") == null) {
                user = createUser(request);
                userDAO.addUser(user);
                employee = createEmployee(request, user);
            } else {
                user = userDAO.getUserById(Integer.parseInt(request.getParameter("employeeId")));
                employee = user.getEmployee();
                fillEmployee(request, employee);
                if (request.getSession().getAttribute("userRole").equals("ADMIN")) {
                    employee.setDepartment(getDepartment(request));
                    employee.setPosition(getPosition(request));
                    employee.setHireDate(LocalDate.parse(request.getParameter("hireDate")));
                }
            }
            user.setEmployee(employee);
            userDAO.updateUser(user);
//            TODO: ajax for save
            response.sendRedirect("EmployeeList");
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

    private Employee createEmployee(HttpServletRequest request, User user) throws ServletException {
        try {
            Employee employee = new Employee();
            employee.setId(user.getId());
            employee.setFirstName(request.getParameter("firstName"));
            employee.setLastName(request.getParameter("lastName"));
            employee.setMiddleName(request.getParameter("middleName"));
            employee.setBirthDate(LocalDate.parse(request.getParameter("birthDate")));
            employee.setEmail(request.getParameter("email"));
            employee.setPhone(request.getParameter("phone"));
            employee.setAddress(request.getParameter("address"));
            employee.setHireDate(LocalDate.parse(request.getParameter("hireDate")));
            employee.setDepartment(getDepartment(request));
            employee.setPosition(getPosition(request));
            return employee;
        } catch (ServletException e) {
            throw new ServletException(e);
        }
    }

    private User createUser(HttpServletRequest request) {
        User user = new User();
        user.setLogin(request.getParameter("email"));
        user.setPassword(BCrypt.hashpw(user.getLogin(), BCrypt.gensalt()));
        user.setRole("USER");
        return user;
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
