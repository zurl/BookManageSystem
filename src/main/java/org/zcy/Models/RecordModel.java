package org.zcy.Models;

import com.sun.org.apache.regexp.internal.RE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.zcy.Entities.BookEntity;

import java.sql.ResultSet;
import java.util.List;

/**
 * Created by zcy on 2017/3/1.
 */
@Component
public class RecordModel {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public RecordModel(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    private static final String QUERY_SQL = "SELECT `bookid`, `type`, `name`, `year`, `press`, `author`, `count`, `current`, `price` FROM `records` NATURAL JOIN `books` WHERE cardid = ? AND status = 0;";

    public List<BookEntity> queryBook(Integer cardId){
        return this.jdbcTemplate.query(
                QUERY_SQL,
                new Object[]{cardId},
                (ResultSet rs, int rowNum)->
                        new BookEntity(
                                rs.getString("bookid"),
                                rs.getString("type"),
                                rs.getString("name"),
                                rs.getString("press"),
                                rs.getString("author"),
                                rs.getInt("current"),
                                rs.getInt("count"),
                                rs.getInt("year"),
                                rs.getDouble("price")
                        )

        );
    }

}
