package pl.lukasziwon;

import java.util.List;

import javax.ws.rs.GET;
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
}
