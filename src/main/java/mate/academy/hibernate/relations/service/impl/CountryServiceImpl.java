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
        CountryDao countryDao = this.countryDao;
        countryDao.add(country);
        return country;
    }

    @Override
    public Country get(Long id) {
        CountryDao countryDao = this.countryDao;
        return countryDao.get(id).orElseThrow(
                () -> new DataProcessingException("cannot get an instance from DB"));
    }
}
