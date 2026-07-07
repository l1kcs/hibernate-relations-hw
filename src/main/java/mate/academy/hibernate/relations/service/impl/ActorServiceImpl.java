package mate.academy.hibernate.relations.service.impl;

import mate.academy.hibernate.relations.dao.ActorDao;
import mate.academy.hibernate.relations.dao.impl.ActorDaoImpl;
import mate.academy.hibernate.relations.exception.DataProcessingException;
import mate.academy.hibernate.relations.model.Actor;
import mate.academy.hibernate.relations.service.ActorService;
import org.hibernate.SessionFactory;

public class ActorServiceImpl implements ActorService {
    private final ActorDao actorDao;

    public ActorServiceImpl(SessionFactory sessionFactory) {
        actorDao = new ActorDaoImpl(sessionFactory);
    }

    @Override
    public Actor add(Actor actor) {
        actorDao.add(actor);
        return actorDao.get(actor.getId()).orElseThrow(
                () -> new DataProcessingException(
                        "cannot get an instance of newly created actor"));
    }

    @Override
    public Actor get(Long id) {
        return actorDao.get(id).orElseThrow(
                () -> new DataProcessingException("cannot get an instance from DB"));
    }
}
