package hu.palkonyves.business.dao;

import java.util.List;
import java.util.Set;

public interface Dao<TYPE,KEY> {

	// //////////////////////////
	//  find
	// //////////////////////////

	/**
	 * Find one entity by a primary key
	 * 
	 * @param id
	 *            primary key
	 * @return the entity
	 */
	public TYPE findById(KEY id);

	/**
	 * Find all entities with this set of primary keys
	 * 
	 * @param ids
	 *            set of primary keys
	 * @return list of entities for the matching primary keys
	 */
	public List<TYPE> findAllById(Set<KEY> ids);

	/**
	 * Return all entities of this type
	 * 
	 * @return list of all entites
	 */
	public List<TYPE> findAll();

	// //////////////////////////
	//  create
	// //////////////////////////

	/**
	 * Creates (persists) a new entity
	 * 
	 * @param entity
	 *            to be persisted
	 * @return attached entity
	 */
	public TYPE create(TYPE entity);

	/**
	 * Persists all entites in this list
	 * 
	 * @param entities
	 *            list of entitis to be persisted
	 * @return list of attached entities
	 */
	public List<TYPE> createAll(List<TYPE> entities);

	// //////////////////////////
	//  merge
	// //////////////////////////

	/**
	 * Merge this entity with the database and attach it
	 * 
	 * @param entity
	 *            to be merged
	 * @return attached entity merged
	 */
	public TYPE merge(TYPE entity);

	/**
	 * Merge all entities to the database and attach them
	 * 
	 * @param entities
	 *            a list of entities to be attached
	 * @return list of attached entities merged
	 */
	public List<TYPE> mergeAll(List<TYPE> entities);

	// //////////////////////////
	//  removes
	// //////////////////////////

	/**
	 * Remove an entity from the database
	 * 
	 * @param entity
	 *            to be removed
	 */
	public void remove(TYPE entity);

	/**
	 * Remove all entites from the database in this list
	 * 
	 * @param entities
	 *            to be removed
	 */
	public void removeAll(List<TYPE> entities);


}
