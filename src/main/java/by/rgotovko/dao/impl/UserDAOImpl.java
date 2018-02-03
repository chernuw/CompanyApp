package by.rgotovko.dao.impl;

import by.rgotovko.dao.DAOException;
import by.rgotovko.dao.UserDAO;
import by.rgotovko.entity.Employee;
import by.rgotovko.entity.User;
import by.rgotovko.util.HibernateUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.NoResultException;
import javax.persistence.criteria.*;
import javax.transaction.Transactional;

public class UserDAOImpl implements UserDAO {
    private static Logger logger = LogManager.getLogger("by.rgotovko.dao");

    @Override
    public User getUserById(Integer id) throws DAOException {
        logger.trace("UserDAOImpl.getUserById(" + id + ")");
        Transaction transaction = null;
        User user;
        try {
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            user = session.get(User.class, id);
            transaction.commit();
            return user;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DAOException(e);
        }
    }

    @Override
    public User getUserByLogin(String login) throws DAOException {
        logger.trace("UserDAOImpl.getUserByLogin(" + login + ")");
        User user;
        Transaction transaction = null;
        try {
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<User> query = builder.createQuery(User.class);
            Root<User> root = query.from(User.class);
            query.select(root);
            query.where(builder.equal(root.get("login"), login));
            try {
                user = session.createQuery(query).getSingleResult();
            } catch (NoResultException e) {
                user = null;
            }
            transaction.commit();
            return user;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DAOException(e);
        }
    }

    @Override
    public void addUser(User user) throws DAOException {
        logger.trace("UserDAOImpl.addUser()");
        Transaction transaction = null;
        try{
            Session session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.save(user);
            transaction.commit();
        } catch (Exception e){
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DAOException(e);
        }
    }

    @Override
    public void updateUser(User user) throws DAOException {
        logger.trace("UserDAOImpl.updateUser(" + user.getId() + ")");
        Transaction transaction = null;
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.update(user);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DAOException(e);
        }
    }

    @Override
    public void deleteUser(User user) throws DAOException {
        logger.trace("UserDAOImpl.deleteUser(" + user.getId() + ")");
        Transaction transaction = null;
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.delete(user);
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
        logger.trace("UserDAOImpl.isValidValue(" + param + "," + value + "," + empId + ")");
        Transaction transaction = null;
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<User> query = builder.createQuery(User.class);
            Root<User> root = query.from(User.class);
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
