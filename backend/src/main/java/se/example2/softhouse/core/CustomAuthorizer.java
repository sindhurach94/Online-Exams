package se.example2.softhouse.core;

/**
 * Created by charan on 9/26/2016.
 */
import com.sun.xml.internal.ws.api.ha.StickyFeature;
import io.dropwizard.auth.Authorizer;
import se.example2.softhouse.DAO.UserRegisterDAO;

import java.util.Objects;


public class CustomAuthorizer implements Authorizer<UserDetails> {
    private UserDetails userDetails;
    private UserRegisterDAO userRegisterDAO;
    public CustomAuthorizer(UserRegisterDAO  userRegisterDAO)
    {
        this.userRegisterDAO=userRegisterDAO;
    }



    @Override
    public boolean authorize(UserDetails userDetails, String role) {

             String userName=userDetails.getUserName();
             String userRole=userRegisterDAO.retrieveOccupation(userName);

        return   Objects.equals(role, userRole) ;
    }
}