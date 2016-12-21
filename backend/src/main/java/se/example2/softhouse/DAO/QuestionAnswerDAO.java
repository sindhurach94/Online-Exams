package se.example2.softhouse.DAO;

/**
 * Created by charan on 9/6/2016.
 */
import org.skife.jdbi.v2.sqlobject.*;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapperFactory;
import org.skife.jdbi.v2.tweak.BeanMapperFactory;
import se.example2.softhouse.core.Choice;
import se.example2.softhouse.core.ExamQuestion;
import se.example2.softhouse.core.Question;
import se.example2.softhouse.core.QuestionAnswer;

import java.util.List;
@RegisterMapperFactory(BeanMapperFactory.class)
public interface QuestionAnswerDAO {
    @SqlUpdate("create table if not exists QuestionAnswer (id int auto_increment primary key,questionId int,choiceId int)")
    void createQuestionAnswerTable();

    @SqlUpdate("delete table QuestionAnswer")
    void deleteQuestionAnswerTable();

    @SqlQuery("select * from QuestionAnswer")
    List<QuestionAnswer> list();

    @SqlUpdate("delete from QuestionAnswer where choiceId = :choiceId")
    void deleteInQuestionAnswer(@Bind("choiceId") long choiceId);

    @SqlUpdate("delete from QuestionAnswer where questionId = :questionId")
    void deleteInQuestionAnswerByQuestionId(@Bind("questionId") long questionId);
    @GetGeneratedKeys
    @SqlUpdate("insert into QuestionAnswer (questionId, choiceId) values (:questionId, :choiceId)")
    long createInQuestionAnswer(@Bind("questionId") long questionId,@Bind("choiceId") long choiceId);

    @SqlQuery("select choiceId from QuestionAnswer where questionId =:questionId")
    Long getChoiceId(@Bind("questionId") long questionId);

    @SqlQuery("select * from QuestionAnswer where questionId =:questionId")
    QuestionAnswer getQuestionAnswer(@Bind("questionId") long questionId);

}
