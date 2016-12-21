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
import java.util.Objects;
import java.util.Optional;

@Path("/exam/{examId}/question")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RolesAllowed({"teacher"})
public class QuestionResource {

    private QuestionDAO questionDAO;
    private ExamQuestionDAO examQuestionDAO;
    private ChoiceDAO choiceDAO;
    private QuestionAnswerDAO questionAnswerDAO;
    private ExamQuestion examQuestion =new ExamQuestion();
    public QuestionResource(QuestionDAO questionDAO, ExamQuestionDAO examQuestionDAO, ChoiceDAO choiceDAO,QuestionAnswerDAO questionAnswerDAO) {
        this.questionDAO = questionDAO;
        this.examQuestionDAO =examQuestionDAO;
        this.choiceDAO=choiceDAO;
        this.questionAnswerDAO=questionAnswerDAO;
    }


    @GET
    public List<Question> list(@Auth UserDetails userDetails, @PathParam("examId") Integer id) {

        return questionDAO.getQuestions(id);
    }

    @GET
    @Path("/{questionId}")
    public Question retrieve(@PathParam("questionId") Long id) {
        return questionDAO.retrieve(id);
    }

    @POST
    public Response create(@PathParam("examId") Long examId, Question question) {

        List<Question>  questionList=questionDAO.getQuestions(examId);

        if(question.getText()==null||question.getText()=="")
            throw new NotFoundException();
       for (int i = 0; i <questionList.size(); i++) {
           Question question1 = questionList.get(i);

            if (Objects.equals(question.getText(), question1.getText())) {
                throw new NotFoundException();
            }

        }

       long questionId = questionDAO.create(question);
            examQuestionDAO.createinExamQuestion(examId, questionId);
        return Response.ok(questionDAO.retrieve(questionId)).build();
    }

    @PUT
    @Path("/{questionId}")
    public Response update(@PathParam("questionId") Long questionId, Question question) {
        Optional<Question> update = Optional.ofNullable(questionDAO.retrieve(questionId));
        if (update.isPresent()) {
            questionDAO.update(questionId, question);
            return Response.ok(questionDAO.retrieve(questionId)).build();
        } else {
            throw new NotFoundException();
        }
    }

    @DELETE
    @Path("/{questionId}")
    public Response delete(@PathParam("examId") long examId,@PathParam("questionId") long questionId) {
        questionDAO.delete(questionId);
        questionDAO.deleteinExamQuestion(examId, questionId);
        Optional<List<Choice>> update = Optional.ofNullable(choiceDAO.getChoices(questionId));
        if (update.isPresent()) {
            choiceDAO.deleteByQuestion(questionId);
            questionAnswerDAO.deleteInQuestionAnswerByQuestionId(questionId);
        }
        return Response.ok().build();
    }
}