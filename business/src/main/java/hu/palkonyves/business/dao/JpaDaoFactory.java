package hu.palkonyves.business.dao;

import hu.palkonyves.persistence.entities.Banana;

import javax.persistence.EntityManager;

class JpaDaoFactory implements DaoFactory {

    private EntityManager em;

    public void init(EntityManager em) {
	this.em = em;
    }

    @Override
    public Dao<Banana, Long> getBananaDao() {
	return new JpaDao<Banana, Long>(em, Banana.class, "id");
    }
}
