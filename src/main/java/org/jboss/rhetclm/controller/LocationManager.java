package org.jboss.rhetclm.controller;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import org.jboss.rhetclm.model.Location;

@Stateless
public class LocationManager {
	
	@PersistenceContext
	private EntityManager em;
	
//	@Inject
//	private LocationRegistration registrationHelper;
	
	public Location add(Location location) throws Exception {
//		if(location == null || location.getCity() == null || location.getCity().equals(""))
//			return null;
		if(find(location.getCity()) == null) {
//			registrationHelper.register(location);
			em.persist(location);
			em.flush();
			return location;
		} else {
			throw new Exception("Location not unique.");
		}
	}
	
	public Location addCity(String cityName) throws Exception {
		return add(new Location(cityName));
	}
		
	@SuppressWarnings("unchecked")
	public List<Location> findAll() {
//		List<Location> l = (List<Location>) em.createQuery("from Location").getResultList();
//		if(l.size() == 0)
//			return null;
//		return l;
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
