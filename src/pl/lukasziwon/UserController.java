package pl.lukasziwon;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/users")
public class UserController {

	@Path("all")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<User> getUsers(){
		return UserDAO.getUsers();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("add")
	public String addUser(@FormParam("Id") int Id,@FormParam("name") String name,
			@FormParam("email") String email,@FormParam("password") String password) {
		
		User user = new User(Id, name, email, password);
		System.out.println(user.toString());
		UserDAO.addUser(user);
		return name;
	}
}
