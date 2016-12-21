package se.example2.softhouse.Resources;

import io.dropwizard.auth.Auth;
import se.example2.softhouse.DAO.UserRegisterDAO;
import se.example2.softhouse.core.UserDetails;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Optional;

/**
 * Created by charan on 9/16/2016.
 */

@Path("/login")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RolesAllowed({"teacher"})
public class UserAuthenticationResource {
    private UserRegisterDAO userRegisterDAO;
    public UserAuthenticationResource(UserRegisterDAO userRegisterDAO){
        this.userRegisterDAO=userRegisterDAO;

    }

    @GET
    public void list() {

    }

    @POST
    public Response registerUser(@Auth UserDetails userDetails) {

        String userName=userDetails.getUserName();
        userDetails=userRegisterDAO.retrieveByUserName(userName);
        userDetails.setPassword("");
        return Response.ok(userDetails).build();
/*        Optional<UserDetails> update = Optional.ofNullable(userRegisterDAO.retrieveUser(userDetails));

        if (update.isPresent()) {
            userDetails=userRegisterDAO.retrieveUser(userDetails);
            userDetails.setPassword("");
            return Response.ok(userDetails).build();
        } else {
            throw new NotFoundException();
        }*/


    }
}
