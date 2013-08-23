package hu.palkonyves.business.service;

import hu.palkonyves.business.dao.Dao;
import hu.palkonyves.business.dao.DaoFactory;
import hu.palkonyves.business.dao.DaoFactoryProvider;
import hu.palkonyves.business.util.CdiTransactionInterceptor;
import hu.palkonyves.persistence.entities.Banana;

import java.util.Collection;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@CdiTransactionInterceptor
@ApplicationScoped
public class BananaService {

    @Inject
    DaoFactoryProvider daoFactoryProvider;
    DaoFactory daoFactory;
    Dao<Banana, Long> bananaDao;

    @PostConstruct
    public void postConstrut() {
	daoFactory = daoFactoryProvider.getJpaFactory();
	bananaDao = daoFactory.getBananaDao();
    }

    public Banana getBananaById(Long id) {
	return bananaDao.findById(id);
    }

    public Collection<Banana> getBananasByName(String name) {
	return null;
    }

    public Collection<Banana> getAllBananas() {
	return bananaDao.findAll();
    }

    public Banana changeBanana(Banana banana) {
	return bananaDao.merge(banana);
    }

    public Banana createBanana(Banana banana) {
	return bananaDao.create(banana);
    }
}
