package pl.lukasziwon;

import java.util.ArrayList;
import java.util.List;

public class UserDAO {

	static List<User> users = new ArrayList<>();
	
	public static List<User> getUsers(){
		
		users.add(new User(1, "Jacek", "Nowak", "jacek@gmail.com"));
		
		return users;
	}

	public static void addUser(User user) {
		users.add(user);
		
	}
}
