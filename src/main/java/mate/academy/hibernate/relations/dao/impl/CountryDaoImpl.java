package mate.academy.hibernate.relations.dao.impl;

import java.util.Optional;
import mate.academy.hibernate.relations.dao.CountryDao;
import mate.academy.hibernate.relations.exception.DataProcessingException;
import mate.academy.hibernate.relations.model.Country;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class CountryDaoImpl extends AbstractDao implements CountryDao {
    public CountryDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public Country add(Country country) {
        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.persist(country);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            throw new DataProcessingException("cannot make a transaction with adding country", e);
        } finally {
            session.close();
        }
        return country;
    }

    @Override
    public Optional<Country> get(Long id) {
        Session session = null;
        try {
            session = factory.openSession();
            Country country = session.get(Country.class, id);
            return Optional.ofNullable(country);
        } catch (Exception e) {
            throw new DataProcessingException("Can't get country by id: " + id, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
