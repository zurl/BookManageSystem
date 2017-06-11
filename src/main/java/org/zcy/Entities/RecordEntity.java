package org.zcy.Entities;

import java.sql.Timestamp;

/**
 * Created by zcy on 2017/3/1.
 */
public class RecordEntity {
    private Integer id;
    private Integer cardid;
    private String bookid;
    private String adminid;
    private Timestamp borrowTime;
    private Timestamp returnTime;
    private Integer status;

    public RecordEntity(Integer id, Integer cardid, String bookid, String adminid, Timestamp borrowTime, Timestamp returnTime, Integer status) {
        this.id = id;
        this.cardid = cardid;
        this.bookid = bookid;
        this.adminid = adminid;
        this.borrowTime = borrowTime;
        this.returnTime = returnTime;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCardid() {
        return cardid;
    }

    public void setCardid(Integer cardid) {
        this.cardid = cardid;
    }

    public String getBookid() {
        return bookid;
    }

    public void setBookid(String bookid) {
        this.bookid = bookid;
    }

    public String getAdminid() {
        return adminid;
    }

    public void setAdminid(String adminid) {
        this.adminid = adminid;
    }

    public Timestamp getBorrowTime() {
        return borrowTime;
    }

    public void setBorrowTime(Timestamp borrowTime) {
        this.borrowTime = borrowTime;
    }

    public Timestamp getReturnTime() {
        return returnTime;
    }

    public void setReturnTime(Timestamp returnTime) {
        this.returnTime = returnTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
