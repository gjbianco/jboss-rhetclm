package org.jboss.rhetclm.service;

import java.util.List;
import java.util.logging.Logger;

import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.jboss.rhetclm.model.Location;

public class LocationManager {
	@Inject
	private Logger log;
	
	@Inject
	private EntityManager em;
	
	@Inject
	private Event<Location> locationEventSrc;
	
	public List<Location> findAllLocations() {
		return (List<Location>) em.createQuery("select * from locations;").getResultList();
	}

	public void register(Location location) {
		log.info("Registering location: " + location.getCity());
		em.persist(location);
		locationEventSrc.fire(location);
	}
}
