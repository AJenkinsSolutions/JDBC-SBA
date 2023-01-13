package jpa.service;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import jpa.dao.HibernateDAO;

public class HibernateUtil {

		public static Session getConnection(SessionFactory factory) {
		Session session = factory.openSession();
		System.out.println("**Successfully Opened Session**");
		
		return session;
				
		}
		public static SessionFactory getSessionFactory() {
			SessionFactory factory = new Configuration().configure().buildSessionFactory();
			System.out.println("Factory created");
			return factory;
		}
		
		public static void disconnect(Session session) {
			session.close();
			System.out.println("Successfully closed session");
		}
	
		public static Transaction beginTransaction(Session session) {
			Transaction trans = session.beginTransaction();
			System.out.println("Successfully begun Transaction");
			return trans;
		}
		
		public static void commitTransaction(Transaction trans) {
			trans.commit();
			
			System.out.println("Successfully Comitted Transaction");
		}
}
		
		
		
