package org.jboss.rhetclm.controller;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.enterprise.inject.Produces;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.jboss.rhetclm.model.Location;

@Model
public class LocationController {
	
	@Inject
	private LocationManager locationManager;
	
	//@Inject
	private FacesContext fc = FacesContext.getCurrentInstance();
	
	@Produces
	@Named
	private static Location newLocation;
	
	@PostConstruct
	private void initNewLocation() {
		newLocation = new Location();
	}
	
	public void register() {
		if(locationManager.add(newLocation) == null) {
			// TODO add Faces message saying that registration failed
		}
		initNewLocation();
	}
	
	public List<Location> list() {
		return locationManager.findAll();
	}
	
}
