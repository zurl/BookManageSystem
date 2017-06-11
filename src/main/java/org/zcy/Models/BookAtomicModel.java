package org.zcy.Models;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by zcy on 2017/3/29.
 */
@Component
@Transactional
public class BookAtomicModel {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public BookAtomicModel(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    private static final String BORROW_CHECK_SQL = "SELECT count(*) FROM `records` WHERE `bookid` = ? AND `cardid` = ? AND `status` = 0;";
    private static final String BORROW_SQL = "INSERT INTO `records`(`cardid`, `bookid`, `adminid`, `borrow_time`, `status`) VALUES (? ,? ,?, now() ,0)";
    private static final String RETURN_SQL = "UPDATE `records` SET `status` = 1, `return_time` = now() WHERE `cardid` = ? AND `bookid` = ? AND `status` = 0 ;";
    private static final String INCREMENT_SQL = "UPDATE `books` SET `current` = `current` + 1 WHERE `bookid` = ?;";
    private static final String DECREMENT_SQL = "UPDATE `books` SET `current` = `current` - 1 WHERE `bookid` = ? AND `current` > 0;";

    public void returnBook(String bookId, Integer cardId){
        this.jdbcTemplate.update(INCREMENT_SQL, bookId);
        this.jdbcTemplate.update(
                RETURN_SQL,
                cardId,
                bookId
        );
    }

    public void borrowBook(String bookId, Integer cardId, String adminId)
    throws SQLException{
        if(this.jdbcTemplate.query(
                BORROW_CHECK_SQL,
                new Object[]{bookId, cardId},
                (ResultSet rs) -> {
                    if(!rs.next())return -1;
                    else return rs.getInt(1);
                }
        ) != 0) throw new SQLException("CANNOT borrow SAME book ");
        int ret = this.jdbcTemplate.update(DECREMENT_SQL, bookId);
        if( ret == 0) throw new SQLException("No Available Book");
        this.jdbcTemplate.update(
                BORROW_SQL,
                cardId,
                bookId,
                adminId
        );
    }

}
