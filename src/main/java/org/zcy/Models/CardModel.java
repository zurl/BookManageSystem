package org.zcy.Models;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.zcy.Entities.CardEntity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by zcy on 2017/3/1.
 */
@Component
public class CardModel {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public CardModel(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static final String QUERY_SQL = "SELECT `cardid`, `name`, `unit`, `type` FROM `cards`";
    private static final String INSERT_SQL = "INSERT INTO `cards`(`name`, `unit`, `type`) VALUES (?, ?, ?);";
    private static final String DELETE_SQL = "DELETE FROM `cards` WHERE `cardid` = ?;";
    private static final String ID_SQL = "SELECT max(`cardid`) FROM `cards`;";
    private static final String EXIST_SQL = "SELECT count(*) FROM `cards` WHERE `cardid` = ?;";
    public List<CardEntity> queryAll(){
        return this.jdbcTemplate.query(
                QUERY_SQL,
                (ResultSet rs, int rowNum)->
                        new CardEntity(
                                rs.getInt("cardid"),
                                rs.getString("name"),
                                rs.getString("unit"),
                                rs.getString("type")
                        )
        );
    }

    public boolean existCard(Integer cardId){
        return this.jdbcTemplate.query(EXIST_SQL,
                new Object[]{cardId},
                (ResultSet rs) ->{
                    if(!rs.next())return -1;
                    else return rs.getInt(1);
                }) == 1;
    }

    public int createCard(String name, String unit, String type){
        int ret = this.jdbcTemplate.update(
                INSERT_SQL,
                name,
                unit,
                type
        );
        if(ret == 0)return -1;
        return this.jdbcTemplate.query(
                ID_SQL,
                (ResultSet rs) ->{
                    if(!rs.next())return -1;
                    else return rs.getInt(1);
                }
        );
    }

    public void deleteCard(Integer cardid)
    throws Exception{
        if(this.jdbcTemplate.update(
                DELETE_SQL,
                cardid
        ) != 1) throw new SQLException("Invalid Card Id");
    }
}
