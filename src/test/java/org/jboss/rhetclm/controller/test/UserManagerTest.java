package org.jboss.rhetclm.controller.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

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
public class UserManagerTest {
	
	private static final User[] sampleUsers = {
			// used in initial data setup (DO NOT PERSIST WITHIN TESTS)
			
			//                        USERNAME, FIRSTNAME,   LASTNAME,  NICKNAME,  LOCATION
			new User("jjj",    "James",     "Jones",   "Jim",     new Location("Charlotte")),
			new User("rbs",    "Robert",    "Smith",   "Bob",     new Location("Raleigh")),
			
			
			// extra users (starts at index = 2)
			
			//                        INITIALS, FIRSTNAME,   LASTNAME,  NICKNAME,  LOCATION
			new User("ttf",    "Tom",       "Fitz",    "Tom",     new Location("Durham")),
			new User("jjw",    "Johnathan", "White",   "John",    new Location("Chapel Hill")),
	};
	
	@Inject
	UserManager userManager;
	
	@PersistenceContext
	EntityManager em;
	
	@Inject
	UserTransaction utx;
	
	// TEST METHODS ---------------------------------------------------------------------
	
	@Test
	public void addUniqueUserTest() {
		User u = getSampleUser(2);
		
		// test add() returned user it was actually persisted
		assertNotEquals(0, userManager.add(u).getId());
	}
	
	@Test
	public void addNonUniqueUserTest() {
		User u = getSampleUser(2);
		
		// change his username to an already persisted username ("jjj")
		u.setUsername("jjj");
		
		// should return null if username isn't unique
		assertEquals(null, userManager.add(u));
		
		// should not have persisted (and, therefore, ID is 0)
		assertEquals(0, u.getId());
	}
	
	@Test
	public void findAllUsersTest() {
		User expected1 = getSampleUser(0);
		User expected2 = getSampleUser(1);
		
		List<User> actual = userManager.findAll();
		
		// make sure we get the correct number of results
		assertEquals(2, actual.size());
		
		// make sure both of our users are found
		assertEquals(expected1.getLastname(), actual.get(0).getLastname());
		assertEquals(expected2.getLastname(), actual.get(1).getLastname());
	}
	
	@Test
	public void findUserTest() {
		User actual = userManager.find("jjj");
		assertEquals(getSampleUser(0).getLastname(), actual.getLastname());
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void removeUserTest() {
		userManager.remove("jjj");
		List<User> actual = (List<User>) em.createQuery("from User").getResultList();
		assertEquals(1, actual.size());
	}
	
	// SETUP METHODS --------------------------------------------------------------------
	
	@Before
	public void setup() throws Exception {
		clearData();
		addSampleData();
		startTransaction();
	}
	
	@After
	public void commitTransaction() throws Exception {
		utx.commit();
	}
	
	private void clearData() throws Exception {
		utx.begin();
		em.joinTransaction();
		
		em.createQuery("delete from User").executeUpdate();
		
		utx.commit();
	}
	
	private void addSampleData() throws Exception {
		utx.begin();
		em.joinTransaction();
		
		em.persist(getSampleUser(0));
		em.persist(getSampleUser(1));
		
		utx.commit();
		em.clear();
	}
	
	private void startTransaction() throws Exception {
		utx.begin();
		em.joinTransaction();
	}
	
	private User getSampleUser(int index) {
		User u = new User();
		u.setUsername(sampleUsers[index].getUsername());
		u.setFirstname(sampleUsers[index].getFirstname());
		u.setLastname(sampleUsers[index].getLastname());
		u.setNickname(sampleUsers[index].getNickname());
		u.setLocation(sampleUsers[index].getLocation());
		return u;
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