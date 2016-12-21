package se.example2.softhouse.DAO;

import org.skife.jdbi.v2.sqlobject.*;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapperFactory;
import org.skife.jdbi.v2.tweak.BeanMapperFactory;
import se.example2.softhouse.core.Exam;
import se.example2.softhouse.core.Question;

import java.util.List;

/**
 * Created by charan on 9/6/2016.
 */
import java.util.List;

@RegisterMapperFactory(BeanMapperFactory.class)
public interface ExamDAO {

    @SqlUpdate("create table if not exists Exam (id Long auto_increment primary key, text varchar(2000),link varchar(50))")
    void createExamTable();

    @SqlUpdate("delete table Exam")
    void deleteExamTable();

    @SqlQuery("select * from Exam")
    List<Exam> list();

    @GetGeneratedKeys
    @SqlUpdate("insert into Exam (id, text) values (:id, :text)")
    long create(@BindBean Exam exam);

    @SqlQuery("select * from Exam where id = :id")
    Exam retrieve(@Bind("id") long id);

    @SqlUpdate("delete from Exam where id = :id")
    void delete(@Bind("id") long id);

    @SqlUpdate("update Exam set text = :u.text where id = :id")
    void update(@Bind("id") long id, @BindBean("u") Exam exam);

    @SqlQuery("select * from Exam where text = :text")
    Exam retrieveByText(@Bind("text") String text);
    @SqlUpdate("update Exam set link = :u.link where id = :id")
    void updateLink(@Bind("id") long id, @BindBean("u") Exam exam);


}