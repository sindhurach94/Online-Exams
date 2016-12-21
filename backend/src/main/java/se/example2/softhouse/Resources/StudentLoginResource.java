package se.example2.softhouse.Resources;

import io.dropwizard.auth.Auth;
import se.example2.softhouse.DAO.UserRegisterDAO;
import se.example2.softhouse.core.Choice;
import se.example2.softhouse.core.UserDetails;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Optional;

/**
 * Created by charan on 9/21/2016.
 */
@Path("/student/takeatest/{examId}/login")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RolesAllowed({"student"})
public class StudentLoginResource {

    private UserRegisterDAO userRegisterDAO;
    public StudentLoginResource(UserRegisterDAO userRegisterDAO){
        this.userRegisterDAO=userRegisterDAO;

    }

    @GET
    public void list() {

    }

    @POST
    public Response registerUser(@Auth UserDetails userDetails) {

      Optional<UserDetails> update = Optional.ofNullable(userRegisterDAO.retrieveUser(userDetails));
        String userName=userDetails.getUserName();

        userDetails=userRegisterDAO.retrieveByUserName(userName);
        userDetails.setPassword("");
        return Response.ok(userDetails).build();
       /* if (update.isPresent()) {
            userDetails=userRegisterDAO.retrieveUser(userDetails);
            userDetails.setPassword("");
            return Response.ok(userDetails).build();
        } else {
            throw new NotFoundException();
        }*/




    }
}
