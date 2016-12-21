package se.example2.softhouse.DAO;

import org.skife.jdbi.v2.sqlobject.*;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapperFactory;
import org.skife.jdbi.v2.tweak.BeanMapperFactory;
import se.example2.softhouse.core.Exam;
import se.example2.softhouse.core.Qfull;
import se.example2.softhouse.core.Question;

import java.util.List;

/**
 * Created by charan on 9/6/2016.
 */
import java.util.List;

@RegisterMapperFactory(BeanMapperFactory.class)
public interface QuestionDAO {

    @SqlUpdate("create table if not exists Question (id Long auto_increment primary key, text varchar(2000))")
    void createQuestionTable();

    @SqlUpdate("delete table Question")
    void deleteQuestionTable();

    @SqlQuery("select * from Question")
    List<Question> list();

    @GetGeneratedKeys
    @SqlUpdate("insert into Question (id, text) values (:id, :text)")
    long create(@BindBean Question question);

    @SqlQuery("select * from Question where id = :id")
    Question retrieve(@Bind("id") long id);

    @SqlUpdate("delete from Question where id = :id")
    void delete(@Bind("id") long id);

    @SqlUpdate("update Question set text = :u.text where id = :id")
    void update(@Bind("id") long id, @BindBean("u") Question question);

    @SqlQuery("SELECT QUESTION.id,QUESTION.text FROM QUESTION JOIN EXAMQUESTION ON QUESTION.ID=EXAMQUESTION.QUESTIONID WHERE EXAMQUESTION.EXAMID= :examId")
    List<Question> getQuestions(@Bind("examId") long examId);

    @SqlUpdate("delete from ExamQuestion where examId = :examId AND questionId = :questionId")
    void deleteinExamQuestion(@Bind("examId") long examId,@Bind("questionId") long questionId);

    @SqlUpdate("insert into ExamQuestion (examId, questionId) values (:examId, :questionId)")
    void createinExamQuestion(@Bind("examId") long examId,@Bind("questionId") long questionId);

    @SqlQuery("SELECT ExamQUESTION.questionId WHERE EXAMQUESTION.EXAMID= :examId")
    List<Long> checkQuestionInExamQuestion(@Bind("examId") long examId);

    @SqlUpdate("delete from ExamQuestion where examId = :examId")
    void deleteinExamQuestionByExamId(@Bind("examId") long examId);

    /*@SqlQuery("select * from Question where id = :id")
    Question retrieve(@Bind("id") long id);*/

    @SqlQuery("select * from Question where text = :text")
    Question retrieveByTextInExam(@Bind("text") String text);

}