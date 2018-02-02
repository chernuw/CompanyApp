package by.rgotovko.controller.position;

import by.rgotovko.dao.DAOException;
import by.rgotovko.dao.DAOFactory;
import by.rgotovko.entity.Position;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "PositionAdd", urlPatterns = "/PositionAdd")
public class PositionAdd extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Position position = new Position();
            position.setTitle(request.getParameter("positionTitle"));
            DAOFactory.getPositionDAO().addPosition(position);
        } catch (DAOException e) {
            throw new ServletException(e);
        }
    }
}
