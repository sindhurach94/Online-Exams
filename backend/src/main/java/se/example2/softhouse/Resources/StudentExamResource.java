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
@Path("/student/takeatest/{examId}/test")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RolesAllowed({"student"})
public class StudentExamResource {
    private QuestionDAO questionDAO;
    private ExamQuestionDAO examQuestionDAO;
    private ChoiceDAO choiceDAO;
    private StudentExamDAO studentExamDAO;
    private QuestionAnswerDAO questionAnswerDAO;
    private UserRegisterDAO userRegisterDAO;

    public StudentExamResource(UserRegisterDAO userRegisterDAO,QuestionDAO questionDAO, ExamQuestionDAO examQuestionDAO, ChoiceDAO choiceDAO, StudentExamDAO studentExamDAO, QuestionAnswerDAO questionAnswerDAO) {
        this.questionDAO = questionDAO;
        this.examQuestionDAO =examQuestionDAO;
        this.choiceDAO=choiceDAO;
        this.studentExamDAO=studentExamDAO;
        this.questionAnswerDAO=questionAnswerDAO;
        this.userRegisterDAO=userRegisterDAO;
    }

    @GET
    public List<Qfull> list(@PathParam("examId") Long id) {
       List<Question> questionList=questionDAO.getQuestions(id);
        List<Qfull> qfullList=new ArrayList<Qfull>();

        for (int i = 0; i <questionList.size(); i++) {
            Question question=questionList.get(i);
            long questionId = question.getId();
             question = questionDAO.retrieve(questionId);
            List<Choice> choices = choiceDAO.getChoices(questionId);
            Qfull qfull = new Qfull(question, choices);
           qfullList.add(qfull);
        }
        return qfullList;
    }

    @GET
    @Path("/{questionId}")
    public Qfull retrieve(@PathParam("questionId") Long questionId)
    {

      Question question = questionDAO.retrieve(questionId);
      List<Choice> choices =choiceDAO.getChoices(questionId);

       Qfull qfull = new Qfull(question,choices);

        return  qfull;
    }

    @POST
    public StudentExam create(@PathParam("examId") Long examId,@Auth UserDetails userDetails, Qfull qfull) {
        long marks =0;long id;
        long selectedId=qfull.getSelectedId();
        long questionId = qfull.getQuestionId();
        long answerId= questionAnswerDAO.getChoiceId(questionId);
        if(selectedId==answerId) {
            marks = 1;
        }
        String userName=userDetails.getUserName();
        long userId=userRegisterDAO.retrieveUserId(userName);
        StudentExam studentExam= new StudentExam(userId,examId,questionId,selectedId,marks);
      /*  id = studentExamDAO.create(studentExam);
        return studentExamDAO.retrieve(id);*/
        Optional<StudentExam> update = Optional.ofNullable(studentExamDAO.retrieveByQuestionId(questionId));
        if (update.isPresent()) {
            studentExamDAO.update(questionId,studentExam);
            id=studentExamDAO.retrieveIdByQuestionId(questionId);
            return studentExamDAO.retrieve(id);

        }
        else {
            id = studentExamDAO.create(studentExam);
            return studentExamDAO.retrieve(id);
        }
    }

    @PUT
    @Path("/{questionId}")
    @Consumes(MediaType.APPLICATION_JSON)
    public StudentExam update(@PathParam("examId") Long examId,@Auth UserDetails userDetails,@PathParam("questionId") Long questionId,Qfull qfull) {
           long marks =0;long id;
       long selectedId=qfull.getSelectedId();
        String userName=userDetails.getUserName();
        long userId=userRegisterDAO.retrieveUserId(userName);
        long answerId= questionAnswerDAO.getChoiceId(questionId);
        if(selectedId==answerId) {
            marks = 1;
        }
        StudentExam studentExam= new StudentExam(userId,examId,questionId,selectedId,marks);
      /*  id = studentExamDAO.create(studentExam);
        return studentExamDAO.retrieve(id);*/
        Optional<StudentExam> update = Optional.ofNullable(studentExamDAO.retrieveByQuestionId(questionId));
        if (update.isPresent()) {
           studentExamDAO.update(questionId,studentExam);
            id=studentExamDAO.retrieveIdByQuestionId(questionId);
           return studentExamDAO.retrieve(id);

        }
        else {
           id = studentExamDAO.create(studentExam);
           return studentExamDAO.retrieve(id);
        }
    }

    @DELETE
    @Path("/{questionId}")
    public void delete() {

    }

    @GET
    @Path("/result")
    public List<StudentExam> retrieve(@Auth UserDetails userDetails,@PathParam("examId") Long examId)
    {
        String userName=userDetails.getUserName();
        long userId=userRegisterDAO.retrieveUserId(userName);
        return studentExamDAO.retrieveAnswers(userId,examId);

    }

}
