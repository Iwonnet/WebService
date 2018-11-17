package pl.lukasziwon;

import java.util.ArrayList;
import java.util.List;

public class UserDAO {

	public static List<User> getUsers(){
		List<User> users = new ArrayList<>();
		users.add(new User(1, "Jacek", "Nowak", "jacek@gmail.com"));
		
		return users;
	}
}
