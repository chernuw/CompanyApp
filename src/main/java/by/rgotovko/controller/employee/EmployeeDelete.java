package by.rgotovko.controller.employee;

import by.rgotovko.dao.DAOException;
import by.rgotovko.dao.DAOFactory;
import by.rgotovko.entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "EmployeeDelete", urlPatterns = "/EmployeeDelete")
public class EmployeeDelete extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws
            ServletException{
        try{
            Integer id = Integer.parseInt(request.getParameter("employeeId"));
            User user = DAOFactory.getUserDAO().getUserById(id);
            user.getEmployee().setDepartment(null);
            user.getEmployee().setPosition(null);
            DAOFactory.getUserDAO().deleteUser(user);
        } catch (DAOException e) {
            throw new ServletException(e);
        }
    }
}
