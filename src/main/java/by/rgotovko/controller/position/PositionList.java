package by.rgotovko.controller.position;

import by.rgotovko.dao.DAOException;
import by.rgotovko.dao.DAOFactory;
import by.rgotovko.entity.Position;
import by.rgotovko.serializer.PositionSerializer;
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

@WebServlet(name = "PositionList", urlPatterns = "/PositionList")
public class PositionList extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<Position> positions = DAOFactory.getPositionDAO().getAllPositions();
            Gson gson = new GsonBuilder()
                    .registerTypeAdapterFactory(HibernateProxyTypeAdapter.FACTORY)
                    .excludeFieldsWithoutExposeAnnotation()
                    .registerTypeAdapter(Position.class, new PositionSerializer())
                    .create();
            response.getWriter().append(gson.toJson(positions));
        } catch (DAOException e) {
            throw new ServletException(e);
        }
    }
}
