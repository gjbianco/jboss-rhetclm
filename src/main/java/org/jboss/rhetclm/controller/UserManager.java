package org.jboss.rhetclm.controller;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import org.jboss.rhetclm.model.User;

@Stateless
public class UserManager {
	
	@PersistenceContext(unitName = "primary")
	EntityManager em;

	/**
	 * Add the User to the underlying datasource. The User must have a unique username.
	 * @param user User to be persisted.
	 * @return Returns the User object back if username is unique; null otherwise.
	 */
	public User add(User user) {
		if(find(user.getUsername()) == null) {
			em.persist(user);
			return user;
		} else {
			// should invalidate input (eventually)
			return null;
		}
	}
	
	/**
	 * Query for a user with the provided username (usernames are unique).
	 * @param username
	 * @return If found, returns the User object, and null, if not.
	 */
	public User find(String username) {
		try {
			return (User) em.createQuery("from User where username = :username")
							.setParameter("username", username).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}
	
	/**
	 * Query for all persisted users.
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<User> findAll() {
		return (List<User>) em.createQuery("from User").getResultList();
	}
	
	/**
	 * Removes user with provided username from persistence (usernames are unique).
	 * @param username
	 */
	public void remove(String username) {
		User u = find(username);
		em.remove(u);
	}
}
