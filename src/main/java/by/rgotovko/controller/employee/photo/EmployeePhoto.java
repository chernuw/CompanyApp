package by.rgotovko.controller.employee.photo;

import by.rgotovko.dao.DAOException;
import by.rgotovko.dao.DAOFactory;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

@WebServlet(name = "EmployeePhoto", urlPatterns = "/EmployeePhoto")
public class EmployeePhoto extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            response.setContentType("image/jpeg, image/jpg, image/png, image/gif");
            byte[] photo = DAOFactory.getEmployeeDAO().getEmployeeById(id).getPhoto();
//            if employee didnt have photo - return default image from resources
            if (photo == null) {
                ServletContext context = getServletContext();
                FileInputStream fin = new FileInputStream(context
                        .getRealPath("/resources/img/no-avatar.png"));
                ServletOutputStream out = response.getOutputStream();
                BufferedInputStream bin = new BufferedInputStream(fin);
                BufferedOutputStream bout = new BufferedOutputStream(out);
                int ch;
                while((ch = bin.read())!=-1){
                    bout.write(ch);
                }
                bin.close();
                fin.close();
                bout.close();
            } else {
                response.getOutputStream().write(photo);
            }
            response.getOutputStream().close();
        } catch (DAOException e) {
            throw new ServletException(e);
        }
    }
}
