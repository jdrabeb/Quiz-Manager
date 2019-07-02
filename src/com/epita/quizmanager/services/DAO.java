package com.epita.quizmanager.services;

import java.sql.Connection;
import java.util.List;

/**
 * DAO - An abstract class of DAO that describes the behavior of DAOs to create.
 * @author rabeb
 *
 * @param <T> Object
 */
public abstract class DAO<T> {
	protected Connection connection = null;
   
	/**
	 * Construction of a DAO.
	 * @param conn - Connection of the database.
	 */
	public DAO(Connection conn)
	{
		this.connection = conn;
	}
   
	/**
	 * Create an object.
	 * @param obj - 
	 */
	public abstract void create(T obj);

	/**
	 * Update an object.
	 * @param obj_id
	 * @param newObj
	 */
	public abstract void update(int obj_id, T newObj);

	/**
	 * Delete an object
	 * @param obj_id
	 * @return 0 if successful
	 */
	public abstract int delete(int obj_id);

	/**
	 * Find a list of objects based on a value.
	 * @param value
	 * @return list of found objects.
	 */
	public abstract List<T> find(String value);
}