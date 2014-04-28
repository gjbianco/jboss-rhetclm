package org.jboss.rhetclm.service.test;

import javax.inject.Inject;

import junit.framework.TestCase;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.rhetclm.model.Location;
import org.jboss.rhetclm.model.User;
import org.jboss.rhetclm.service.LocationManager;
import org.jboss.rhetclm.service.UserManager;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)
public class UserManagerTest extends TestCase {

	@Deployment
	public static WebArchive createDeployment() {
		WebArchive war = ShrinkWrap
				.create(WebArchive.class, "test.war")
				.addClasses(User.class, Location.class,
							UserManager.class, LocationManager.class)
				.addAsResource("META-INF/test-persistence.xml", "META-INF/persistence.xml")
				.addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml")
				.addAsWebInfResource("test-ds.xml");
		return war;
	}
	
	@Inject
	User user;
	
	@Inject
	UserManager userManager;
	
	@Test
	public void addUserTest() {
		userManager.register(user);
//		User found = findUserByQuery(user.getUsername());
		
//		Assert.assertEquals(found, user);
		Assert.assertNotNull(user.getId());
	}
	
//	@Test
//	public void findAllUsersTest() {
//		List<User> found = userManager.findAllUsers();
//	}
	
	@Before
	public void initUser() {
		user.setFirstname("Robert");
		user.setLastname("Smith");
		user.setNickname("Bob");
		user.setUsername("rbs");
	}
	
//	public User findUserByQuery(String username) {
//		User found = (User) em.createQuery("select * from user where username=" + username)
//				.getSingleResult();
//		return found;
//	}

}
