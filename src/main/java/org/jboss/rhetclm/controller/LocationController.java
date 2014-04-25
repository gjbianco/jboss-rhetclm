package org.jboss.rhetclm.controller;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.enterprise.inject.Produces;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.jboss.rhetclm.model.Location;
import org.jboss.rhetclm.service.LocationManager;

@Model
public class LocationController {
	@Inject
	private FacesContext facesContext;
	
	@Inject
	private LocationManager locationManager;
	
	@Produces
	@Named
	private Location location;
	
	@PostConstruct
	public void initNewLocation() {
		location = new Location();
	}
	
	@Named
	public List<Location> locations() {
		return locationManager.findAllLocations();
	}
	
	public void register() {
		locationManager.register(location);
	}
}
