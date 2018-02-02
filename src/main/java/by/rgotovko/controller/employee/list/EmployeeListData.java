package by.rgotovko.controller.employee.list;

import by.rgotovko.dao.DAOException;
import by.rgotovko.dao.DAOFactory;
import by.rgotovko.entity.Department;
import by.rgotovko.entity.Employee;
import by.rgotovko.entity.Position;
import by.rgotovko.serializer.DepartmentSerializer;
import by.rgotovko.serializer.EmployeeSerializer;
import by.rgotovko.serializer.PositionSerializer;
import by.rgotovko.util.HibernateProxyTypeAdapter;
import com.google.gson.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "EmployeeListData", urlPatterns = "/EmployeeListData")
public class EmployeeListData extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException {
        try {
            String page = request.getParameter("page");
            String amountOnPage = request.getParameter("amountOnPage");
            String orderField = request.getParameter("orderField");
            List<Employee> employees = DAOFactory.getEmployeeDAO()
                    .getAllEmployees(Integer.parseInt(page), Integer.parseInt(amountOnPage),orderField);
            Gson gson = new GsonBuilder()
                    .registerTypeAdapterFactory(HibernateProxyTypeAdapter.FACTORY)
                    .excludeFieldsWithoutExposeAnnotation()
                    .registerTypeAdapter(Employee.class, new EmployeeSerializer())
                    .registerTypeAdapter(Department.class, new DepartmentSerializer())
                    .registerTypeAdapter(Position.class, new PositionSerializer())
                    .create();
            response.getWriter().append(gson.toJson(employees));
        } catch (DAOException e) {
            throw new ServletException(e);
        }
    }
}
