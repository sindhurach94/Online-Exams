package se.example2.softhouse.Application;

import com.bazaarvoice.dropwizard.assets.ConfiguredAssetsBundle;
import io.dropwizard.Application;
import io.dropwizard.auth.AuthDynamicFeature;
import io.dropwizard.auth.AuthValueFactoryProvider;
import io.dropwizard.auth.basic.BasicCredentialAuthFilter;
import io.dropwizard.jdbi.DBIFactory;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.eclipse.jetty.server.session.SessionHandler;
import org.glassfish.jersey.server.filter.RolesAllowedDynamicFeature;
import org.h2.tools.Server;
import org.skife.jdbi.v2.DBI;
import se.example2.softhouse.DAO.*;
import se.example2.softhouse.DAO.QuestionAnswerDAO;
import se.example2.softhouse.Resources.*;
import se.example2.softhouse.core.CustomAuthenticator;
import se.example2.softhouse.core.CustomAuthorizer;
import se.example2.softhouse.core.UserDetails;
import java.sql.SQLException;
import java.util.Optional;

public class DemoApplication extends Application<DemoConfiguration> {

    @Override
    public void run(DemoConfiguration configuration, Environment environment) throws SQLException {
        Server myH2adminGUI = org.h2.tools.Server.createWebServer("-webDaemon");
        myH2adminGUI.start();
        final DBIFactory factory = new DBIFactory();
        final DBI jdbi = factory.build(environment, configuration.getDataSourceFactory(), "h2");
        final QuestionDAO questionDAO = jdbi.onDemand(QuestionDAO.class);
        final ChoiceDAO choiceDAO = jdbi.onDemand(ChoiceDAO.class);
        final QuestionAnswerDAO questionAnswerDAO = jdbi.onDemand(QuestionAnswerDAO.class);
        final ExamDAO examDAO = jdbi.onDemand(ExamDAO.class);
        final ExamQuestionDAO examQuestionDAO= jdbi.onDemand(ExamQuestionDAO.class);
        final UserRegisterDAO userRegisterDAO= jdbi.onDemand(UserRegisterDAO.class);
        final StudentExamDAO studentExamDAO=jdbi.onDemand(StudentExamDAO.class);
       questionDAO.createQuestionTable();
        choiceDAO.createChoiceTable();
        questionAnswerDAO.createQuestionAnswerTable();   //initial table creations
        examDAO.createExamTable();
        examQuestionDAO.createExamQuestionTable();
        userRegisterDAO.createUserTable();
        studentExamDAO.createStudentExamTable();
        environment.jersey().register(new ExamResource(examDAO,questionDAO,choiceDAO,examQuestionDAO));
       environment.jersey().register(new QuestionResource(questionDAO,examQuestionDAO,choiceDAO,questionAnswerDAO));
        environment.jersey().register(new ChoiceResource(choiceDAO,questionAnswerDAO));
        environment.jersey().register(new UserRegisterResource(userRegisterDAO,studentExamDAO));
        environment.jersey().register(new UserAuthenticationResource(userRegisterDAO));
        environment.jersey().register(new StudentExamResource(userRegisterDAO,questionDAO,examQuestionDAO,choiceDAO,studentExamDAO,questionAnswerDAO));
        environment.jersey().register(new StudentLoginResource(userRegisterDAO));
        environment.jersey().register(new ResultResource(userRegisterDAO,questionDAO,examQuestionDAO,choiceDAO,studentExamDAO,questionAnswerDAO));

        UserDetails userDetails= new UserDetails("charan","ypcharan3@gmail.com","charan","teacher");
        Optional<UserDetails> update = Optional.ofNullable(userRegisterDAO.retrieveByUserName(userDetails.getUserName()));
        if(update.isPresent())
        {

        }
        else
        {
            userRegisterDAO.create(userDetails);
        }
        environment.jersey().register(new AuthDynamicFeature(new BasicCredentialAuthFilter.Builder<UserDetails>()
                .setAuthenticator(new CustomAuthenticator(userRegisterDAO))
                .setAuthorizer(new CustomAuthorizer(userRegisterDAO))
                .setRealm("SUPER SECRET STUFF")
                .buildAuthFilter()));
        environment.jersey().register(RolesAllowedDynamicFeature.class);
        //environment.jersey().register(new LoginResource());
        //If you want to use @Auth to inject a custom Principal type into your resource
        environment.jersey().register(new AuthValueFactoryProvider.Binder<>(UserDetails.class));



       }

    @Override
    public void initialize(Bootstrap<DemoConfiguration> configuration) {
        configuration.addBundle(new ConfiguredAssetsBundle("/assets/", "/", "index.html"));
    }

    public static void main(String[] args) throws Exception {
        new DemoApplication().run(args);
    }
}




