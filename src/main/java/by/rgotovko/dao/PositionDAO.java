package by.rgotovko.dao;

import by.rgotovko.entity.Position;

import java.util.List;

public interface PositionDAO {

    Position getPositionByTitle(String title) throws DAOException;

    void addPosition(Position position) throws DAOException;

    List<Position> getAllPositions() throws DAOException;
}
