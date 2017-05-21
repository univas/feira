package br.edu.univas.si.model.dao;

import javax.persistence.EntityManager;

import br.edu.univas.si.model.entities.Course;
import br.edu.univas.si.model.entities.UserInfo;

public class UserDAO {

	// @PersistenceContext(unitName = "unit_app")
	EntityManager em = HibernateUtil.getSessionFactory().createEntityManager();

	public void saveUser(UserInfo user) {
		em.getTransaction().begin();
		em.persist(user);
		em.getTransaction().commit();
	}

	public void saveCourse(Course course) {
		em.getTransaction().begin();
		em.persist(course);
		em.getTransaction().commit();
	}

	public UserInfo retrieveUser(String email) {
		return em.find(UserInfo.class, email);
	}

}
