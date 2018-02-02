package by.rgotovko.dao;

import by.rgotovko.entity.Employee;

import java.util.List;

public interface EmployeeDAO {
    List<Employee> getAllEmployees() throws DAOException;

    List<Employee> getAllEmployees(int numberOfPage, int amountOnPage, String orderField) throws
            DAOException;

    Employee getEmployeeById(Integer id) throws DAOException;

    void updateEmployee(Employee employee) throws DAOException;

    void deleteEmployee(Employee employee) throws DAOException;

    void addEmployee(Employee employee) throws DAOException;

    boolean isValidValue(String param, String value, Integer empId) throws DAOException;

}
