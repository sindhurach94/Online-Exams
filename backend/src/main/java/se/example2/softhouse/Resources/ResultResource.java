package se.example2.softhouse.Resources;

import io.dropwizard.auth.Auth;
import se.example2.softhouse.DAO.*;
import se.example2.softhouse.core.*;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by charan on 9/21/2016.
 */
@Path("/student/takeatest/{examId}/result")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RolesAllowed({"student"})
public class ResultResource {
    private QuestionDAO questionDAO;
    private ExamQuestionDAO examQuestionDAO;
    private ChoiceDAO choiceDAO;
    private StudentExamDAO studentExamDAO;
    private QuestionAnswerDAO questionAnswerDAO;
    private UserRegisterDAO userRegisterDAO;

    public ResultResource(UserRegisterDAO userRegisterDAO, QuestionDAO questionDAO, ExamQuestionDAO examQuestionDAO, ChoiceDAO choiceDAO, StudentExamDAO studentExamDAO, QuestionAnswerDAO questionAnswerDAO) {
        this.questionDAO = questionDAO;
        this.examQuestionDAO = examQuestionDAO;
        this.choiceDAO = choiceDAO;
        this.studentExamDAO = studentExamDAO;
        this.questionAnswerDAO = questionAnswerDAO;
        this.userRegisterDAO = userRegisterDAO;
    }

    @GET
    public List<StudentExam> retrieve(@Auth UserDetails userDetails,@PathParam("examId") Long examId)
    {
        String userName=userDetails.getUserName();
        long userId=userRegisterDAO.retrieveUserId(userName);
        return studentExamDAO.retrieveAnswers(userId,examId);

    }
    @POST
    public void registerUser(@Auth UserDetails userDetails) {
    }
}