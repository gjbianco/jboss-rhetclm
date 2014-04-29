package org.jboss.rhetclm.service.test;

import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.rhetclm.model.Location;
import org.jboss.rhetclm.model.User;
import org.jboss.rhetclm.service.UserManager;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)
public class UserManagerTest {

	@Deployment
	public static WebArchive createDeployment() {
		WebArchive war = ShrinkWrap
				.create(WebArchive.class, "test.war")
				.addClasses(User.class, UserManager.class,
							Location.class)
				.addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml")
				.addAsResource("META-INF/persistence.xml");
		System.out.println(war.toString(true));
		return war;
	}
	
	User user;
	
	@Inject
	UserManager userManager;
	
	@Test
	public void addUserTest() {
		User user = new User();
		user.setFirstname("Robert");
		user.setLastname("Smith");
		user.setNickname("Bob");
		user.setUsername("rbs");
		
		userManager.register(user);
		Assert.assertNotNull(user.getId());
	}
		
//	@Before
//	public void initUser() {
//		System.out.println("begin initUser");
//		user = new User();
//		user.setFirstname("Robert");
//		user.setLastname("Smith");
//		user.setNickname("Bob");
//		user.setUsername("rbs");
//		System.out.println("after initUser");
//	}

	
//	public User findUserByQuery(String username) {
//		User found = (User) em.createQuery("select * from user where username=" + username)
//				.getSingleResult();
//		return found;
//	}

}