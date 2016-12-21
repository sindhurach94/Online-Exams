package se.example2.softhouse.Resources;

import se.example2.softhouse.DAO.ChoiceDAO;
import se.example2.softhouse.DAO.ExamDAO;
import se.example2.softhouse.DAO.ExamQuestionDAO;
import se.example2.softhouse.DAO.QuestionDAO;
import se.example2.softhouse.core.Choice;
import se.example2.softhouse.core.Exam;
import se.example2.softhouse.core.Question;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Optional;

@Path("/exam")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RolesAllowed({"teacher"})
public class ExamResource {
    private ExamDAO examdao;
    private QuestionDAO questionDAO;
    private ChoiceDAO choiceDAO;
    private ExamQuestionDAO examQuestionDAO;
    public ExamResource(ExamDAO examDAO,QuestionDAO questionDAO,ChoiceDAO choiceDAO,ExamQuestionDAO examQuestionDAO) {
        this.examdao = examDAO;this.questionDAO=questionDAO;this.examQuestionDAO=examQuestionDAO;
    }


    @GET
    public List<Exam> list() {
        return examdao.list();
    }

    @GET
    @Path("/{examId}")
    public Exam retrieve(@PathParam("examId") Long id) {
        return examdao.retrieve(id);
    }

    @POST
    public Response create(Exam exam) {
        Optional<Exam> update = Optional.ofNullable(examdao.retrieveByText(exam.getText()));

        if(exam.getText()==null||exam.getText()=="")
            throw new NotFoundException();
        if (update.isPresent()) {
            throw new NotFoundException();
        } else {

            long examId = examdao.create(exam);
            exam.setLink("/api/student/takeatest/"+examId+"/login");
            examdao.updateLink(examId,exam);
            return Response.ok(examdao.retrieve(examId)).build();

        }

    }

    @PUT
    @Path("/{examId}")
    public Response update(@PathParam("examId") long id, Exam exam) {
        Optional<Exam> update = Optional.ofNullable(examdao.retrieve(id));

        if (update.isPresent()) {
            examdao.update(id, exam);
            return Response.ok(examdao.retrieve(id)).build();
        } else {
            throw new NotFoundException();
        }
    }

    @DELETE
    @Path("/{examId}")
    public Response delete(@PathParam("examId") long id) {
        examdao.delete(id);
        Optional<List<Long>> update = Optional.ofNullable(examQuestionDAO.checkQuestionInExamQuestion(id));
        if (update.isPresent()) {
            examQuestionDAO.deleteinExamQuestionByExamId(id);

          /*  for(Integer questionId : update) {               //deleting for each question
                questionDAO.delete(questionId);
                choiceDAO.deleteByQuestion(questionId);
                choiceDAO.deleteInQuestionAnswerByQuestionId(questionId);
            }*/
        }
        return Response.ok().build();
    }
}