package jpa.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;

public interface HibernateDAO {
	
	
	
	
	Session getConnection();
	
	void disconnect(Session session);
	
	Transaction beginTransaction(Session session);
	
	void commitTransaction(Transaction trans);
	
	
	
}
