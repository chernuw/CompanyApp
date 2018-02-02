package by.rgotovko.dao;

import by.rgotovko.dao.impl.*;

public class DAOFactory {
    public static EmployeeDAO getEmployeeDAO() {
        return new EmployeeDAOImpl();
    }

    public static DepartmentDAO getDepartmentDAO() {
        return new DepartmentDAOImpl();
    }

    public static UserDAO getUserDAO() {
        return new UserDAOImpl();
    }

    public static PositionDAO getPositionDAO() {
        return new PositionDAOImpl();
    }
}

