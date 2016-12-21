package se.example2.softhouse.DAO;

/**
 * Created by charan on 9/6/2016.
 */
import org.skife.jdbi.v2.sqlobject.*;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapperFactory;
import org.skife.jdbi.v2.tweak.BeanMapperFactory;
import se.example2.softhouse.core.Choice;
import se.example2.softhouse.core.Question;

import java.util.List;

@RegisterMapperFactory(BeanMapperFactory.class)
public interface ChoiceDAO {
    @SqlUpdate("create table if not exists Choice (id Long auto_increment primary key, text varchar(2000),questionId Long)")
    void createChoiceTable();

    @SqlUpdate("delete table Choice")
    void deleteChoiceTable();

    @SqlQuery("select * from Choice")
    List<Choice> list();

    @GetGeneratedKeys
    @SqlUpdate("insert into Choice (id, text, questionId) values (:id, :text, :questionId)")
    long create(@BindBean Choice choice,@Bind("questionId") long questionId);

    @SqlQuery("select * from Choice where id = :id")
    Choice retrieve(@Bind("id") long id);

    @SqlUpdate("delete from Choice where id = :id")
    void delete(@Bind("id") long id);

    @SqlUpdate("update Choice set text = :u.text where id = :id")
    void update(@Bind("id") long id, @BindBean("u") Choice choice);


    @SqlQuery("select * from Choice where questionId = :questionId")
    List<Choice> getChoices(@Bind("questionId") long questionId);

    /*@SqlQuery("select * from Choice where questionId = :questionId")
    List<Choice> getChoices(@Bind("questionId") long questionId);*/


    @SqlUpdate("delete from QuestionAnswer where choiceId = :choiceId")
    void deleteInQuestionAnswer(@Bind("choiceId") long choiceId);

    @SqlUpdate("delete from QuestionAnswer where questionId = :questionId")
    void deleteInQuestionAnswerByQuestionId(@Bind("questionId") long questionId);
    @GetGeneratedKeys
    @SqlUpdate("insert into QuestionAnswer (questionId, choiceId) values (:questionId, :choiceId)")
    long createInQuestionAnswer(@Bind("questionId") long questionId,@Bind("choiceId") long choiceId);

    @SqlUpdate("delete from Choice where questionId = :questionId")
    void deleteByQuestion(@Bind("questionId") long questionId);



}
