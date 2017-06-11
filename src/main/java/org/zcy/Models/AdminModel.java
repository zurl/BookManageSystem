package org.zcy.Models;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.zcy.Entities.AdminEntity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by zcy on 2017/3/1.
 */

@Component
public class AdminModel {

    JdbcTemplate jdbcTemplate;

    @Autowired
    public AdminModel(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static final String QUERY_SQL = "SELECT `adminid`, `name`, `contact` FROM `admins`";
    private static final String LOGIN_SQL = "SELECT count(*) FROM `admins` WHERE `adminid` = ? AND `password` = SHA2(?, 256);";
    private static final String ADD_USER_SQL = "INSERT INTO `admins` (`adminid`, `password`, `name`, `contact`) VALUES (?, SHA2(?, 256), ?, ?);";
    private static final String DELETE_USER_SQL = "DELETE FROM `admins` WHERE `adminid` = ?;";


    public List<AdminEntity> queryAll(){
        return this.jdbcTemplate.query(
                QUERY_SQL,
                (ResultSet rs, int rowNum)->
                        new AdminEntity(
                            rs.getString("adminid"),
                            "",
                            rs.getString("name"),
                            rs.getString("contact")
                    )
        );
    }

    public boolean login(String userName, String passWord){
        if(userName.equals("") || passWord.equals("")) return false;
        return this.jdbcTemplate.query(
                LOGIN_SQL,
                new Object[]{userName, passWord},
                (ResultSet rs) -> {
                    if(!rs.next())return -1;
                    else return rs.getInt(1);
                }
        ) != 0;
    }

    public void addUser(String userName, String passWord, String name, String contact)
    throws Exception{
        if(userName.equals("") || passWord.equals("") ||
           this.jdbcTemplate.update(
                ADD_USER_SQL,
                userName,
                passWord,
                name,
                contact
        ) == 0) throw new SQLException("Invalid User Info");
    }

    public void deleteUser(String userName)throws Exception{
        if(userName.equals("") ||
            this.jdbcTemplate.update(
                DELETE_USER_SQL,
                userName
        )== 0) throw new SQLException("Invalid User Info");
    }
}
