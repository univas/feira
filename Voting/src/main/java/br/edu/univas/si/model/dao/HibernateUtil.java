package br.edu.univas.si.model.dao;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class HibernateUtil {

	private static final EntityManagerFactory sessionFactory = buildSessionFactory();

	private static EntityManagerFactory buildSessionFactory() {
		try {
			// Create the SessionFactory
			return Persistence.createEntityManagerFactory("unit_app");
		} catch (Throwable ex) {
			// Make sure you log the exception, as it might be swallowed
			System.err.println("Initial SessionFactory creation failed." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}

	public static EntityManagerFactory getSessionFactory() {
		return sessionFactory;
	}
}