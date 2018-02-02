package by.rgotovko.dao;

import by.rgotovko.entity.Department;
import by.rgotovko.entity.Employee;

import java.util.List;

public interface DepartmentDAO {
    List<Department> getAllDepartments() throws DAOException;

    Department getDepartmentByName(String name) throws DAOException;

    void addDepartment(Department department) throws DAOException;

}
