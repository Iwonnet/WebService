package pl.lukasziwon;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;

@Path("/users")
public class UserController {

	@Path("all")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<User> getUsers() {
		return UserDAO.getUsers();
	}

	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("add")
	public String addUser(@FormParam("Id") int Id, @FormParam("name") String name, @FormParam("email") String email,
			@FormParam("password") String password) {

		User user = new User(Id, name, email, password);
		System.out.println(user.toString());
		UserDAO.addUser(user);
		return name;
	}

	@Path("all/hibernate")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<User> getAllUsers() {
		return UserDAO.getAllUsers();
	}

	@Path("addhib")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public String addUserHibernate(@FormParam("name") String name, @FormParam("password") String password,
			@FormParam("email") String email) {
		UserDAO.addUserHibernate(name, password, email);
		return "New user added to database!";
	}

	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	@Path("delete/{name}")
	public String deleteUserHibernate(@PathParam("name") String name) {
		UserDAO.deleteUserHibernate(name);
		return "user " + name + " deleted!";
	}

	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("twitter")
	public String searchForTweets() throws TwitterException,JsonProcessingException {
		
		
		
		Twitter twitter = TwitterFactory.getSingleton();
	    Query query = new Query("#smog");
	    query.setCount(6);
	    
	    QueryResult result = twitter.search(query);
	    for (Status status : result.getTweets()) {
	        System.out.println("@" + status.getUser().getScreenName() + "\n" + status.getText());
	    }
	    ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.INDENT_OUTPUT, true);
		return mapper.writeValueAsString(result.getTweets());
        
	}
}
