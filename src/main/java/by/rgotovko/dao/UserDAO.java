package by.rgotovko.dao;

import by.rgotovko.entity.Employee;
import by.rgotovko.entity.User;

public interface UserDAO {
    User getUserById(Integer id) throws DAOException;

    User getUserByLogin(String login) throws DAOException;

    void addUser(User user) throws DAOException;

    void updateUser(User user) throws DAOException;

    boolean isValidValue(String param, String value, Integer empId) throws DAOException;
}
