package se.example2.softhouse.Application.Note;

/**
 * Created by charan on 8/31/2016.
 */

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;

public interface addquestionDAO {



    @SqlUpdate("create table something (id int primary key, name varchar(100))")
    void createTable();

    @SqlUpdate("insert into something (id, name) values (:id, :name)")
    void insert(@Bind("id") int id, @Bind("name") String name);

    @SqlQuery("select name from something where id = :id")
    String findNameById(@Bind("id") int id);

    /**
     * close with no args is used to close the connection
     */
    void close();

}
