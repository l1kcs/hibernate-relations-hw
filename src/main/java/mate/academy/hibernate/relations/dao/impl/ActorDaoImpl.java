package mate.academy.hibernate.relations.dao.impl;

import java.util.Optional;
import mate.academy.hibernate.relations.dao.ActorDao;
import mate.academy.hibernate.relations.exception.DataProcessingException;
import mate.academy.hibernate.relations.model.Actor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class ActorDaoImpl extends AbstractDao implements ActorDao {
    public ActorDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public Actor add(Actor actor) {
        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.persist(actor);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            throw new DataProcessingException("cannot make a transaction", e);
        } finally {
            session.close();
        }
        return actor;
    }

    @Override
    public Optional<Actor> get(Long id) {
        Session session = null;
        try {
            session = factory.openSession();
            Actor actor = session.get(Actor.class, id);
            return Optional.ofNullable(actor);
        } catch (Exception e) {
            throw new DataProcessingException("Can't get actor by id: " + id, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
