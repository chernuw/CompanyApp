package by.rgotovko.controller.user;

import by.rgotovko.dao.DAOException;
import by.rgotovko.dao.DAOFactory;
import by.rgotovko.entity.Employee;
import by.rgotovko.entity.User;
import org.mindrot.jbcrypt.BCrypt;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "UserChangePassword", urlPatterns = "/UserChangePassword")
public class UserChangePassword extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String currentInputPassword = request.getParameter("currentPassword");
            String newInputPassword = request.getParameter("newPassword");
            String empId = request.getParameter("idUser");
            User user = DAOFactory.getUserDAO().getUserById(Integer.parseInt(empId));
            String currentPassword = user.getPassword();
            if (BCrypt.checkpw(currentInputPassword, currentPassword)){
                user.setPassword(BCrypt.hashpw(newInputPassword, BCrypt.gensalt()));
                DAOFactory.getUserDAO().updateUser(user);
                response.getWriter().write("success");
            } else {
                response.getWriter().write("FAIL: Invalid current password");
            }
        } catch (DAOException e) {
            throw new ServletException(e);
        }
    }
}
