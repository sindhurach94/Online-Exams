package se.example2.softhouse.core;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.NotEmpty;

import java.security.Principal;

/**
 * Created by charan on 9/16/2016.
 */
public class UserDetails implements Principal {
    @JsonProperty
    private Long id;

    @JsonProperty
    @NotEmpty
    private String userName;

    @JsonProperty
    @NotEmpty
    private String emailAddress;

    @JsonProperty
    @NotEmpty
    private String password;

    @JsonProperty
    @NotEmpty
    private String occupation;

    public UserDetails()
    {

    }
    public UserDetails(String userName)
    {
        this.userName=userName;

    }
    public UserDetails(String userName,String emailAddress,String password,String occupation)
    {
        this.userName=userName;
        this.emailAddress=emailAddress;
        this.password=password;
        this.occupation=occupation;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    @Override
    public String getName() {
        return null;
    }
}

