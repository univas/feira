package br.edu.univas.si.model.dao;

import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class EntityManagerProducer {

	@PersistenceContext(unitName = "unit_app")
	private EntityManager entityManager;

	@Produces
	public EntityManager entityManager() {
		System.out.println();
		return entityManager;
	}
}
