package mate.academy.hibernate.relations.service.impl;

import mate.academy.hibernate.relations.dao.CountryDao;
import mate.academy.hibernate.relations.dao.impl.CountryDaoImpl;
import mate.academy.hibernate.relations.exception.DataProcessingException;
import mate.academy.hibernate.relations.model.Country;
import mate.academy.hibernate.relations.service.CountryService;
import org.hibernate.SessionFactory;

public class CountryServiceImpl implements CountryService {
    private final CountryDao countryDao;

    public CountryServiceImpl(SessionFactory sessionFactory) {
        countryDao = new CountryDaoImpl(sessionFactory);
    }

    @Override
    public Country add(Country country) {
        countryDao.add(country);
        return countryDao.get(country.getId()).orElseThrow(
                () -> new DataProcessingException(
                        "cannot get an instance of newly created country: " + country.getId()));
    }

    @Override
    public Country get(Long id) {
        return countryDao.get(id).orElseThrow(
                () -> new DataProcessingException(
                        "cannot get an instance of country " + id + " from DB"));
    }
}
