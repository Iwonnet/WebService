package pl.lukasziwon;

import java.util.ArrayList;
import java.util.List;

public class UserDAO {

	static List<User> users = new ArrayList<>();
	
	public static List<User> getUsers(){
		
		return users;
	}

	public static void addUser(User user) {
		users.add(user);
		
	}

	
}
