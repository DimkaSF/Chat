package messenger.resources;

import com.google.gson.Gson;
import messenger.model.Profile;
import messenger.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import messenger.database.ConnectionDB;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Антон on 25.11.2017.
 */
@Path("/profiles")
public class ProfileResource {

    ProfileService prfService = new ProfileService();
    ConnectionDB con = new ConnectionDB();
    Gson gson = new Gson();



    @GET
    @Produces("application/json")
    public Response getProfiles() throws SQLException{

        String output=gson.toJson(prfService.getAllProfiles());
        return Response.status(200).entity(output).build();
    }


    @GET
    @Path("/reg")
    @Produces("application/json")
    public void addUser(

            @QueryParam("username") String username,
            @QueryParam("firstname") String firstname,
            @QueryParam("lastname") String lastname ) throws SQLException {

        //prfService.addProfile(2,new Profile(2,username,firstname,lastname));
        con.CreateTableProfile();
        con.CreateUser(username, firstname,lastname);
        System.out.println("Username:" + username);
        System.out.println("Firstname:" + firstname);
        System.out.println("Lastname:" + lastname);
        //dao.AddProfile(username, firstname, lastname);
        //return Response.status(200).entity("successful").build();
    }

    @GET
    @Path("users/{username}")
    @Produces("application/json")
    public Response testlogin(@PathParam("username") String username) throws SQLException{
        String output=gson.toJson(con.checkUser(username));
        //String output=gson.toJson(prfService.getProfile(username).getId());
        return Response.status(200).entity(output).build();
    }

    @GET
    @Path("/getUsername")
    @Produces("application/json")
    public Response getUsername(@QueryParam("username") String username) throws SQLException{

        String output=gson.toJson(prfService.getProfile(username));
        //String output = gson.toJson(con.checkUser(username));
        return Response.status(200).entity(output).build();

    }


}
