package by.rgotovko.dao.impl;

import by.rgotovko.dao.DAOException;
import by.rgotovko.dao.PositionDAO;
import by.rgotovko.entity.Position;
import by.rgotovko.util.HibernateUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class PositionDAOImpl implements PositionDAO {
    private static Logger logger = LogManager.getLogger("by.rgotovko.dao");

    @Override
    public Position getPositionByTitle(String title) throws DAOException {
        logger.trace("PositionDAOImpl.getPositionByTitle(" + title + ")");
        Transaction transaction = null;
        try {
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            transaction= session.beginTransaction();
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Position> query = builder.createQuery(Position.class);
            Root<Position> root = query.from(Position.class);
            query.select(root).where(builder.equal(root.get("title"), title));
            Position position =session.createQuery(query).getSingleResult();
            transaction.commit();
            return position;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DAOException(e);
        }
    }

    @Override
    public void addPosition(Position position) throws DAOException {
        logger.trace("PositionDAOImpl.addPosition(" + position.getTitle() + ")");
        Transaction transaction = null;
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.save(position);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DAOException(e);
        }
    }

    @Override
    public List<Position> getAllPositions() throws DAOException {
        logger.trace("PositionDAOImpl.getAllPositions()");
        Transaction transaction = null;
        try {
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Position> query = builder.createQuery(Position.class);
            Root<Position> root = query.from(Position.class);
            query.select(root);
            List<Position> positions = session.createQuery(query).getResultList();
            transaction.commit();
            return positions;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DAOException(e);
        }
    }
}
