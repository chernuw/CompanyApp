package by.rgotovko.controller.employee.profile;

import by.rgotovko.dao.DAOException;
import by.rgotovko.dao.DAOFactory;
import by.rgotovko.entity.Department;
import by.rgotovko.entity.Employee;
import by.rgotovko.entity.Position;
import by.rgotovko.entity.User;
import by.rgotovko.serializer.DepartmentSerializer;
import by.rgotovko.serializer.EmployeeSerializer;
import by.rgotovko.serializer.PositionSerializer;
import by.rgotovko.serializer.UserSerializer;
import by.rgotovko.util.HibernateProxyTypeAdapter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "EmployeeProfileData", urlPatterns = "/EmployeeProfileData")
public class EmployeeProfileData extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Integer employeeId = Integer.parseInt(request.getParameter("id"));
            Employee employee = DAOFactory.getEmployeeDAO().getEmployeeById(employeeId);
            Gson gson = new GsonBuilder()
                    .registerTypeAdapterFactory(HibernateProxyTypeAdapter.FACTORY)
                    .excludeFieldsWithoutExposeAnnotation()
                    .registerTypeAdapter(Employee.class, new EmployeeSerializer())
                    .registerTypeAdapter(Department.class, new DepartmentSerializer())
                    .registerTypeAdapter(Position.class, new PositionSerializer())
                    .registerTypeAdapter(User.class, new UserSerializer())
                    .create();
            response.getWriter().append(gson.toJson(employee));
        } catch (DAOException e) {
            throw new ServletException(e);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
