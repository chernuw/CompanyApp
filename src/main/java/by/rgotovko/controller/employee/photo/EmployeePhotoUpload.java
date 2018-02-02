package by.rgotovko.controller.employee.photo;

import by.rgotovko.dao.DAOException;
import by.rgotovko.dao.DAOFactory;
import by.rgotovko.entity.Employee;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.*;
import java.util.Scanner;

@WebServlet(name = "EmployeePhotoUpload", urlPatterns = "/EmployeePhotoUpload")
@MultipartConfig(maxFileSize = 1024 * 1024 * 5, // 5MB
        maxRequestSize = 1024 * 1024 * 10) // 10MB
public class EmployeePhotoUpload extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException {
        try {
            Part oldEmployee = request.getPart("idEmployee");
            Scanner s = new Scanner(oldEmployee.getInputStream());
            String str = s.nextLine();
            Integer id = Integer.parseInt(str);
            Employee employee = DAOFactory.getEmployeeDAO().getEmployeeById(id);
            Part part = request.getPart("uploadingPhoto");
            InputStream is = part.getInputStream();
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            int read;
            final byte[] bytes = new byte[1024];
            while ((read = is.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }
            employee.setPhoto(out.toByteArray());
            DAOFactory.getEmployeeDAO().updateEmployee(employee);
        } catch (DAOException e) {
            throw new ServletException(e);
        }
    }
}
