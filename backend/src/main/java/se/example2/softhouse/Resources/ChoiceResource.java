package se.example2.softhouse.Resources;

/**
 * Created by charan on 9/12/2016.
 */
import se.example2.softhouse.DAO.*;
import se.example2.softhouse.core.*;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.NoContentException;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.OptionalLong;

@Path("/exam/{examId}/question/{questionId}/choice")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RolesAllowed({"teacher"})
public class ChoiceResource {
  private ChoiceDAO choiceDAO;
    private  QuestionAnswerDAO questionAnswerDAO;
        public ChoiceResource(ChoiceDAO choiceDAO,QuestionAnswerDAO questionAnswerDAO ) {
        this.choiceDAO = choiceDAO;
            this.questionAnswerDAO=questionAnswerDAO;
    }
    @GET
    public List<Choice> list(@PathParam("questionId") Long questionId) {

        return choiceDAO.getChoices(questionId);
    }
    @POST
    public Response create(@PathParam("questionId") Long questionId, Choice choice) {
        long choiceId;


        List<Choice> choiceList=choiceDAO.getChoices(questionId);

        for (int i = 0; i <choiceList.size(); i++) {
            Choice choice1 = choiceList.get(i);
            if (Objects.equals(choice.getText(), choice1.getText())) {
                throw new NotFoundException();
            }

        }
      if(choice.getIsCorrect()==null||choice.getText()==null||choice.getText()=="")
          throw new NotFoundException();

        if (choice.getIsCorrect()== 1)
      {
             if(questionAnswerDAO.getChoiceId(questionId)!=null)
             {
                 return Response.ok().build();
             }
             else {
                 choiceId = choiceDAO.create(choice, questionId);
                 long choiceid = questionAnswerDAO.createInQuestionAnswer(questionId, choiceId);
             }

      }
      else {
           choiceId = choiceDAO.create(choice,questionId);
      }
        return Response.ok(choiceDAO.retrieve(choiceId)).build();


    }

    @GET
    @Path("/{choiceId}")
    public Choice retrieve(@PathParam("choiceId") Long id) {
        return choiceDAO.retrieve(id);
    }



    @PUT
    @Path("/{choiceId}")
    public Response update(@PathParam("choiceId") Long choiceId,Choice choice) {
        Optional<Choice> update = Optional.ofNullable(choiceDAO.retrieve(choiceId));

        if (update.isPresent()) {
            choiceDAO.update(choiceId, choice);
            return Response.ok(choiceDAO.retrieve(choiceId)).build();
        } else {
            throw new NotFoundException();
        }
    }

    @DELETE
    @Path("/{choiceId}")
    public Response delete(@PathParam("choiceId") Long choiceId,@PathParam("questionId") Long questionId) {
        choiceDAO.delete(choiceId);

        long choiceIdold=questionAnswerDAO.getChoiceId(questionId);
     //   questionAnswerDAO.deleteInQuestionAnswer(choiceId);

            if(choiceIdold==choiceId) {
                questionAnswerDAO.deleteInQuestionAnswer(choiceId);
            }

        return Response.ok().build();
    }

    @GET
    @Path("/correctChoiceId")
    public Response retrieveCorrectchoice(@PathParam("questionId") Long questionId) {
        Optional<Long> update = Optional.ofNullable(questionAnswerDAO.getChoiceId(questionId));
        if (update.isPresent()) {

            return Response.ok(questionAnswerDAO.getChoiceId(questionId)).build();
        } else {
            throw new NotFoundException();
        }

    }


}
