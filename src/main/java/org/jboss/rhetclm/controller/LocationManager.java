package org.jboss.rhetclm.controller;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import org.jboss.rhetclm.model.Location;

@Stateless
public class LocationManager {
	
	@PersistenceContext(unitName = "primary")
	private EntityManager em;
	
	public Location add(Location location) {
		if(find(location.getCity()) == null) {
			em.persist(location);
			return location;
		} else {
			return null;
		}
	}
		
	@SuppressWarnings("unchecked")
	public List<Location> findAllLocations() {
		return (List<Location>) em.createQuery("from Location").getResultList();
	}
	
	public Location find(String city) {
		try {
			return (Location) em.createQuery("from Location where city = :city")
								.setParameter("city", city).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}
	
	public void remove(String city) {
		Location l = find(city);
		em.remove(l);
	}
}
