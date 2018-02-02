package by.rgotovko.dao.impl;

import by.rgotovko.dao.DAOException;
import by.rgotovko.dao.EmployeeDAO;
import by.rgotovko.entity.Employee;
import by.rgotovko.util.HibernateUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class EmployeeDAOImpl implements EmployeeDAO {
    private static Logger logger = LogManager.getLogger("by.rgotovko.dao");

    @Override
    public List<Employee> getAllEmployees() throws DAOException {
        logger.trace("EmployeeDAOImpl.getAllEmployees()");
        Transaction transaction = null;
        try {
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Employee> query = builder.createQuery(Employee.class);
            Root<Employee> root = query.from(Employee.class);
            query.select(root);
            List<Employee> employees =session.createQuery(query).getResultList();
            transaction.commit();
            return  employees;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DAOException(e);
        }
    }

    @Override
    public List<Employee> getAllEmployees(int numberOfPage, int amountOnPage, String orderField)
            throws DAOException {
        logger.trace("EmployeeDAOImpl.getAllEmployees(" + numberOfPage + "," + amountOnPage + ","
                + orderField + ")");
        Transaction transaction = null;
        try {
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Employee> query = builder.createQuery(Employee.class);
            Root<Employee> root = query.from(Employee.class);
            query.select(root);
            query.orderBy(builder.asc(root.get(orderField)));
            List<Employee> employees = session.createQuery(query)
                    .setFirstResult(numberOfPage * amountOnPage - amountOnPage)
                    .setMaxResults(amountOnPage)
                    .getResultList();
            transaction.commit();
            return employees;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DAOException(e);
        }
    }

    @Override
    public Employee getEmployeeById(Integer id) throws DAOException {
        logger.trace("EmployeeDAOImpl.getEmployeeById(" + id + ")");
        Transaction transaction = null;
        try {
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            Employee employee = session.get(Employee.class, id);
            transaction.commit();
            return employee;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DAOException(e);
        }
    }

    @Override
    public void updateEmployee(Employee employee) throws DAOException {
        logger.trace("EmployeeDAOImpl.updateEmployee(" + employee.getId() + ")");
        Transaction transaction = null;
        try {
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            session.update(employee);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DAOException(e);
        }
    }

    @Override
    public void deleteEmployee(Employee employee) throws DAOException {
        logger.trace("EmployeeDAOImpl.deleteEmployee(" + employee.getId() + ")");
        Transaction transaction = null;
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.delete(employee);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DAOException(e);
        }
    }

    @Override
    public void addEmployee(Employee employee) throws DAOException {
        logger.trace("EmployeeDAOImpl.addEmployee()");
        Transaction transaction = null;
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.save(employee);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DAOException(e);
        }
    }

    @Override
    public boolean isValidValue(String param, String value, Integer empId) throws DAOException {
        logger.trace("EmployeeDAOImpl.isValidValue(" + param + "," + value + "," + empId + ")");
        Transaction transaction = null;
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Employee> query = builder.createQuery(Employee.class);
            Root<Employee> root = query.from(Employee.class);
            query.select(root).where(builder.equal(root.get(param), value));
            boolean valid = session.createQuery(query).getResultList().isEmpty()
                    || session.createQuery(query).getSingleResult().getId().equals(empId);
            transaction.commit();
            return valid;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DAOException(e);
        }
    }
}

