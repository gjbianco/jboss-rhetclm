package org.jboss.rhetclm.controller.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.rhetclm.controller.LocationManager;
import org.jboss.rhetclm.controller.UserManager;
import org.jboss.rhetclm.model.Location;
import org.jboss.rhetclm.model.User;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)
public class LocationManagerTest {
	
	private static Location[] sampleLocations = {
		
		// used in initial data setup (DO NOT PERSIST WITHIN TESTS)
		new Location("Charlotte"),
		new Location("Raleigh"),
		
		// extra locations (starts at index = 2)
		new Location("Durham"),
		new Location("Chapel Hill"),
	};
	
	@Inject
	private LocationManager locationManager;
	
	@PersistenceContext
	EntityManager em;
	
	@Inject
	UserTransaction utx;
	
	// TEST METHODS -----------------------------------------------------------
	
	@Test
	public void addUniqueLocationTest() throws Exception {
		Location l = getSampleLocation(2);
		
		// make sure location is returned and was persisted
		assertNotEquals(0, locationManager.add(l).getId());
	}
	
	@Test(expected = Exception.class)
	public void addNonUniqueLocationTest() throws Exception {
		Location l = getSampleLocation(2);
		l.setCity("Charlotte");
		
		// make sure add() returned null
		assertNull(locationManager.add(l));
		
		// make sure location was not persisted (and, therefore, ID is 0)
		assertEquals(0, l.getId());
	}
	
	@Test
	public void findLocationTest() {
		assertEquals("Charlotte", locationManager.find("Charlotte").getCity());
	}
	
	@Test
	public void findAllTest() {
		List<Location> actual = locationManager.findAll();
		
		// make sure we get the correct number of results
		assertEquals(2, actual.size());
		
		// make sure both locations are found
		assertEquals("Charlotte", actual.get(0).getCity());
		assertEquals("Raleigh", actual.get(1).getCity());
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void removeLocationTest() {
		locationManager.remove("Charlotte");
		List<Location> actual = (List<Location>) em.createQuery("from Location").getResultList();
		assertEquals(1, actual.size());
	}

	// HELPER METHODS ---------------------------------------------------------
	
	@Before
	public void setup() throws Exception {
		clearData();
		addSampleLocations();
	}
	
	private void clearData() throws Exception {
		utx.begin();
		em.joinTransaction();
		
		em.createQuery("delete from Location").executeUpdate();
		
		utx.commit();
	}
	
	private void addSampleLocations() throws Exception {
		utx.begin();
		em.joinTransaction();
		
		em.persist(getSampleLocation(0));
		em.persist(getSampleLocation(1));
		
		utx.commit();
		em.clear();
	}
	
	private Location getSampleLocation(int index) {
		return new Location(sampleLocations[index].getCity());
	}
	
	@Deployment
	public static Archive<?> createTestArchive() {
		WebArchive war = ShrinkWrap
				.create(WebArchive.class, "test.war")
				.addClasses(User.class, UserManager.class,
							Location.class, LocationManager.class)
				.addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml")
				.addAsWebInfResource("test-ds.xml")
				.addAsResource("META-INF/test-persistence.xml", "META-INF/persistence.xml");
		System.out.println(war.toString(true));
		return war;
	}
	
}
