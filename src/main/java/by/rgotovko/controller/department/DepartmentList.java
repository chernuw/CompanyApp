package by.rgotovko.controller.department;

import by.rgotovko.dao.DAOException;
import by.rgotovko.dao.DAOFactory;
import by.rgotovko.entity.Department;
import by.rgotovko.serializer.DepartmentSerializer;
import by.rgotovko.util.HibernateProxyTypeAdapter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "DepartmentList", urlPatterns = "/DepartmentList")
public class DepartmentList extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<Department> departments = DAOFactory.getDepartmentDAO().getAllDepartments();
            Gson gson = new GsonBuilder()
                    .registerTypeAdapterFactory(HibernateProxyTypeAdapter.FACTORY)
                    .excludeFieldsWithoutExposeAnnotation()
                    .registerTypeAdapter(Department.class, new DepartmentSerializer())
                    .create();
            response.getWriter().append(gson.toJson(departments));
        } catch (DAOException e) {
            throw new ServletException(e);
        }
    }
}
