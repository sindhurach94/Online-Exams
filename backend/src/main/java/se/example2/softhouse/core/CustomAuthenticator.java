package se.example2.softhouse.core;

/**
 * Created by charan on 9/26/2016.
 */

import javax.ws.rs.NotFoundException;
import java.util.Optional;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import io.dropwizard.auth.Authenticator;
import io.dropwizard.auth.basic.*;
import io.dropwizard.auth.basic.BasicCredentials;
import org.eclipse.jetty.server.Authentication;

import javax.naming.AuthenticationException;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Response;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import io.dropwizard.auth.basic.*;
import se.example2.softhouse.DAO.UserRegisterDAO;


public class CustomAuthenticator implements Authenticator<io.dropwizard.auth.basic.BasicCredentials, UserDetails> {
    private UserDetails userDetailsnew;
    private UserRegisterDAO userRegisterDAO;
    public CustomAuthenticator(UserRegisterDAO  userRegisterDAO)
    {
        this.userRegisterDAO=userRegisterDAO;
    }
    @Override
    public Optional<UserDetails> authenticate(BasicCredentials credentials) throws io.dropwizard.auth.AuthenticationException {

        String password=credentials.getPassword();
        String userName=credentials.getUsername();
        Optional<UserDetails> update = Optional.ofNullable(userRegisterDAO.retrieveUser(userName,password));

        if (update.isPresent()) {

            return Optional.of(new UserDetails(credentials.getUsername()));
        } else {
            throw new NotFoundException();
        }

    }
}
