package hu.palkonyves.business.dao;


import hu.palkonyves.persistence.entities.Banana;


public interface DaoFactory {

    Dao<Banana, Long> getBananaDao();
}
