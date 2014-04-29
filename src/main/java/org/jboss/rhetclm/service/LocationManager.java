package org.jboss.rhetclm.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.jboss.rhetclm.model.Location;

@Stateless
public class LocationManager {
	
	@PersistenceContext(unitName = "primary")
	private EntityManager em;
	
	@Inject
	private Event<Location> locationEventSrc;
	
	@SuppressWarnings("unchecked")
	public List<Location> findAllLocations() {
		return (List<Location>) em.createQuery("select * from locations;").getResultList();
	}

	public void register(Location location) {
		em.persist(location);
		locationEventSrc.fire(location);
	}
}
