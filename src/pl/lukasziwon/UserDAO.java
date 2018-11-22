package pl.lukasziwon;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class UserDAO {

	static List<User> users = new ArrayList<>();

	public static List<User> getUsers() {

		return users;
	}

	public static void addUser(User user) {
		users.add(user);

	}

	public static List<User> getAllUsers() {
		SessionFactory sessionFactory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(User.class)
				.buildSessionFactory();

		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();

		try {
			users = session.createCriteria(User.class).list();
			session.getTransaction().commit();
		} catch (Exception e) {

		} finally {
			session.close();
			sessionFactory.close();
		}

		return users;
	}

	public static void addUserHibernate(String name, String password, String email) {
		SessionFactory sessionFactory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(User.class)
				.buildSessionFactory();
		
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		
		try {
			User user = new User();
			user.setName(name);
			user.setEmail(email);
			user.setPassword(password);
			
			
			session.save(user);
			session.getTransaction().commit();
		} catch (Exception e) {

		} finally {
			session.close();
			sessionFactory.close();
		}
	}
	
	

}
