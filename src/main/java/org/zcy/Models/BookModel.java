package org.zcy.Models;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.zcy.Entities.BookEntity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by zcy on 2017/3/1.
 */
@Component
public class BookModel {

    JdbcTemplate jdbcTemplate;

    @Autowired
    public BookModel(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static final String INSERT_SQL = "INSERT INTO `books`(`bookid`, `type`, `name`, `year`, `press`, `author`, `count`, `current`, `price`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);";
    private static final String UPDATE_SQL = "UPDATE `books` SET `count` =  `count` + ?, `current`= `current` + ?  WHERE `bookid` = ?";
    private static final String[] FINDBY_SQL = {
                " `type` like ? ",
                " `name` like ? ",
                " `press` like ? ",
                " `author` like ? ",
                " `price` >= ? ",
                " `price` <= ? ",
                " `year` >= ? ",
                " `year` <= ? "
        };
    private static final String FINDBY_BOOKID_SQL = "SELECT * FROM `books` WHERE `bookid` = ?;";


    public int insertBook(Object[] parameters) {

        return this.jdbcTemplate.update(
                INSERT_SQL,
                parameters
        );
    }

    public int updateBook(Integer count, String bookid) {
        return this.jdbcTemplate.update(
                UPDATE_SQL,
                count,
                count,
                bookid
        );
    }

    public List<BookEntity> queryBook(
        Object[] parameters,
        Integer parameterCount
    ){
        Object[] arguments = new Object[parameterCount];
        StringBuilder sqlBuilder = new StringBuilder();
        int now = 0;
        sqlBuilder.append("SELECT * FROM `books` ");
        for(int i = 0 ; i < parameters.length; i ++){
            if( parameters[i] != null){
                if( now == 0 ){
                    sqlBuilder.append(" WHERE ");
                }else{
                    sqlBuilder.append(" AND ");
                }
                sqlBuilder.append(FINDBY_SQL[i]);
                if(i < 4){
                    arguments[now++] = "%" + parameters[i] + "%";
                }
                else{
                    arguments[now++] = parameters[i];

                }
            }
        }
        sqlBuilder.append(";");
        System.out.println(sqlBuilder.toString());
        return this.jdbcTemplate.query(
                sqlBuilder.toString(),
                arguments,
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
