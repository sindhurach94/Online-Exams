package se.example2.softhouse.Resources;


import io.dropwizard.auth.Auth;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapperFactory;
import org.skife.jdbi.v2.tweak.BeanMapperFactory;
import se.example2.softhouse.DAO.StudentExamDAO;
import se.example2.softhouse.DAO.UserRegisterDAO;
import se.example2.softhouse.core.Question;
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


@Path("/login/register")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RolesAllowed({"teacher"})
public class UserRegisterResource {

  private UserRegisterDAO userRegisterDAO;
    private StudentExamDAO studentExamDAO;
    public UserRegisterResource(UserRegisterDAO userRegisterDAO,StudentExamDAO studentExamDAO){
     this.userRegisterDAO=userRegisterDAO;
        this.studentExamDAO=studentExamDAO;

    }

    @GET
    public List<UserDetails> list() {
        return userRegisterDAO.list();
    }

    @POST
     public UserDetails registerUser(UserDetails userDetails)
    {
        Optional<UserDetails> update = Optional.ofNullable(userRegisterDAO.retrieveByUserName(userDetails.getUserName()));

        if (update.isPresent()) {
            throw new NotFoundException();

        } else {

            long userId = userRegisterDAO.create(userDetails);
             String occupation=userDetails.getOccupation();
             return userRegisterDAO.retrieve(userId) ;
        }


    }

}
