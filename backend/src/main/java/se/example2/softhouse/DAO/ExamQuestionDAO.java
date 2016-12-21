package se.example2.softhouse.DAO;

import org.skife.jdbi.v2.sqlobject.*;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapperFactory;
import org.skife.jdbi.v2.tweak.BeanMapperFactory;
import se.example2.softhouse.core.Exam;
import se.example2.softhouse.core.ExamQuestion;
import se.example2.softhouse.core.Question;

import java.util.List;

/**
 * Created by charan on 9/6/2016.
 */
import java.util.List;

@RegisterMapperFactory(BeanMapperFactory.class)
public interface ExamQuestionDAO {

    @SqlUpdate("create table if not exists ExamQuestion (id Long auto_increment primary key, examId Long ,questionId Long)")
    void createExamQuestionTable();

    @SqlUpdate("delete table ExamQuestion")
    void deleteExamQuestionTable();

    @SqlQuery("select * from ExamQuestion")
    List<ExamQuestion> list();


    /* @SqlQuery("select * from Exam where (id) =(:id)")
     Exam retrieve(@Bind("id") int id);
     @GetGeneratedKeys
     @SqlUpdate("insert into Exam (id, text) values (:id, :text)")
     int create(@BindBean Exam exam);
     @SqlUpdate("delete from Exam where (id)=(:id)")
     void destroy(@BindBean Exam exam);
     @SqlUpdate("update Exam set (text)=(:text) where (id)=(:id)")
     void update(@BindBean Exam exam);
     @SqlQuery("select (id) from Exam where (text)=(:text)")
     Long get(@BindBean Exam exam );
 */
    @GetGeneratedKeys
    @SqlUpdate("insert into ExamQuestion (id, examId, questionId) values (:id, :examId, :questionId)")
    long create(@BindBean ExamQuestion examQuestion);

    @SqlQuery("select * from ExamQuestion where id = :id")
    ExamQuestion retrieve(@Bind("id") long id);

    @SqlUpdate("delete from ExamQuestion where id = :id")
    void delete(@Bind long id);

    @SqlUpdate("update ExamQuestion set questionId = :u.questionId where id = :id")
    void update(@Bind("id") long id, @BindBean("u") ExamQuestion examQuestion);

    @SqlUpdate("delete from ExamQuestion where examId = :examId AND questionId = :questionId")
    void deleteinExamQuestion(@Bind("examId") long examId,@Bind("questionId") long questionId);

    @SqlUpdate("insert into ExamQuestion (examId, questionId) values (:examId, :questionId)")
    void createinExamQuestion(@Bind("examId") long examId,@Bind("questionId") long questionId);

    @SqlQuery("SELECT questionId from ExamQuestion WHERE EXAMQUESTION.EXAMID= :examId")
    List<Long> checkQuestionInExamQuestion(@Bind("examId") long examId);

    @SqlUpdate("delete from ExamQuestion where examId = :examId")
    void deleteinExamQuestionByExamId(@Bind("examId") long examId);

}