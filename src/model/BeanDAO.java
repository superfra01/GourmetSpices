package model;

import java.sql.SQLException;
import java.util.Collection;

public interface BeanDAO<T,U> {

	public void doSave(T data) throws SQLException;

	public boolean doDelete(U code) throws SQLException;

	public T doRetrieveByKey(U code) throws SQLException;
	
	public Collection<T> doRetrieveAll(String order) throws SQLException;
}