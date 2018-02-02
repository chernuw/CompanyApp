package by.rgotovko.dao.impl;

import by.rgotovko.dao.DAOException;
import by.rgotovko.dao.DepartmentDAO;
import by.rgotovko.entity.Department;
import by.rgotovko.util.HibernateUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class DepartmentDAOImpl implements DepartmentDAO {
    private static Logger logger = LogManager.getLogger("by.rgotovko.dao");

    @Override
    public List<Department> getAllDepartments() throws DAOException {
        logger.trace("DepartmentDAOImpl.getAllDepartments()");
        Transaction transaction = null;
        try {
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Department> query = builder.createQuery(Department.class);
            Root<Department> root = query.from(Department.class);
            query.select(root);
            List<Department> departments = session.createQuery(query).getResultList();
            transaction.commit();
            return departments;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DAOException(e);
        }
    }

    @Override
    public Department getDepartmentByName(String name) throws DAOException {
        logger.trace("DepartmentDAOImpl.getDepartmentByName()");
        Transaction transaction = null;
        try {
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Department> query = builder.createQuery(Department.class);
            Root<Department> root = query.from(Department.class);
            query.select(root).where(builder.equal(root.get("name"), name));
            Department department = session.createQuery(query).getSingleResult();
            transaction.commit();
            return department;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DAOException(e);
        }
    }

    @Override
    public void addDepartment(Department department) throws DAOException {
        logger.trace("DepartmentDAOImpl.addDepartment(" + department.getName() + ")");
        Transaction transaction = null;
        try{
            Session session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.save(department);
            transaction.commit();
        } catch (Exception e){
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DAOException(e);
        }
    }
}
