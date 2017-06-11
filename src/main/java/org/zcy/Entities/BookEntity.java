package org.zcy.Entities;

/**
 * Created by zcy on 2017/3/1.
 */
public class BookEntity {

    private String bookid;
    private String type;
    private String name;
    private String press;
    private String author;
    private Integer current;
    private Integer count;
    private Integer year;
    private Double price;

    public BookEntity(String bookid, String type, String name, String press, String author, Integer current, Integer count, Integer year, Double price) {
        this.bookid = bookid;
        this.type = type;
        this.name = name;
        this.press = press;
        this.author = author;
        this.current = current;
        this.count = count;
        this.year = year;
        this.price = price;
    }

    public String getBookid() {
        return bookid;
    }

    public void setBookid(String bookid) {
        this.bookid = bookid;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPress() {
        return press;
    }

    public void setPress(String press) {
        this.press = press;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Integer getCurrent() {
        return current;
    }

    public void setCurrent(Integer current) {
        this.current = current;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
