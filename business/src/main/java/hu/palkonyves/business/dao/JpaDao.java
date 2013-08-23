package hu.palkonyves.business.dao;


import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class JpaDao<TYPE, KEY> implements Dao<TYPE, KEY> {

	EntityManager em;
	Class<TYPE> clazz;
	final String idFieldName;

	public JpaDao(EntityManager em, Class<TYPE> clazz, String idFieldName){
		this.em = em;
		this.clazz = clazz;
		this.idFieldName = idFieldName;
	}

	@Override
	public TYPE findById(KEY id) {
		TYPE e = em.find(clazz, id);
		return e;
	}

	@Override
	public List<TYPE> findAllById(Set<KEY> ids){
		CriteriaBuilder critBuilder = em.getCriteriaBuilder();
		CriteriaQuery<TYPE> cq = critBuilder.createQuery(clazz);
		Root<TYPE> root = cq.from(clazz);

		cq.select(root);
		cq.where(root.get(idFieldName).in(ids));
		TypedQuery<TYPE> q = em.createQuery(cq);
		List<TYPE> result = q.getResultList();

		return result;
	}

	@Override
	public List<TYPE> findAll() {
		CriteriaBuilder critBuilder = em.getCriteriaBuilder();
		CriteriaQuery<TYPE> cq = critBuilder.createQuery(clazz);
		Root<TYPE> root = cq.from(clazz);
		cq.select(root);
		TypedQuery<TYPE> q = em.createQuery(cq);
		List<TYPE> result = q.getResultList();

		return result;
	}

	@Override
	public TYPE create(TYPE entity) {
		em.persist(entity);
		return entity;
	}

	@Override
	public List<TYPE> createAll(List<TYPE> entities) {
		for (TYPE type : entities) {
			create(type);
		}
		return entities;
	}

	@Override
	public TYPE merge(TYPE entity) {
		return em.merge(entity);
	}

	@Override
	public List<TYPE> mergeAll(List<TYPE> entities) {
		List<TYPE> newList = new ArrayList<TYPE>();
		for (TYPE type : newList) {
			TYPE merged = merge(type);
			newList.add(merged);
		}
		return newList;
	}

	@Override
	public void remove(TYPE entity) {
		em.remove(entity);
	}

	@Override
	public void removeAll(List<TYPE> entities) {
		for (TYPE entity : entities) {
			remove(entity);
		}
	}

}
