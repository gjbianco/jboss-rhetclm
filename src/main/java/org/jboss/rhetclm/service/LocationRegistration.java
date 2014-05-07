package org.jboss.rhetclm.service;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.jboss.rhetclm.model.Location;

@Stateless
public class LocationRegistration {
	
	@PersistenceContext
	private EntityManager em;
	
	public Location register(Location location) {
		em.persist(location);
		return location;
	}
}
