package org.zcy.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.zcy.Entities.AdminEntity;
import org.zcy.Entities.BookEntity;
import org.zcy.Entities.CardEntity;
import org.zcy.Entities.CodeEntity;
import org.zcy.Models.*;
import org.zcy.UserContext;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by zcy on 2017/3/1.
 */

@RestController
@RequestMapping("/admin")
public class AdminController{

    BookModel bookModel;
    CardModel cardModel;
    AdminModel adminModel;
    RecordModel recordModel;
    BookAtomicModel bookAtomicModel;
    HttpSession httpSession;

    @Autowired
    public AdminController(BookModel bookModel, CardModel cardModel, AdminModel adminModel, RecordModel recordModel, BookAtomicModel bookAtomicModel, HttpSession httpSession) {
        this.bookModel = bookModel;
        this.cardModel = cardModel;
        this.adminModel = adminModel;
        this.recordModel = recordModel;
        this.bookAtomicModel = bookAtomicModel;
        this.httpSession = httpSession;
    }

    @GetMapping
    public String root(@RequestAttribute UserContext userContext){
        System.out.println("asbc");
        return userContext.getUser();
    }

    @PostMapping("/book")
    public CodeEntity borrowRecord(
            @RequestParam(name="bookid")  String bookid,
            @RequestParam(name="count") Integer count
    ) throws Exception{
        return new CodeEntity(bookModel.updateBook(count, bookid));
    }

    @PutMapping("/book")
    public CodeEntity insertBook(
            @RequestParam(name="bookid")  String bookid,
            @RequestParam(name="type") String type,
            @RequestParam(name="name") String name,
            @RequestParam(name="year") Integer year,
            @RequestParam(name="press") String press,
            @RequestParam(name="author") String author,
            @RequestParam(name="count") Integer count,
            @RequestParam(name="price") Double price
    ) throws Exception{
        return new CodeEntity(bookModel.insertBook(
                new Object[]{
                        bookid, type, name, year, press, author,
                        count, count, price
                }
        ));
    }

    @GetMapping("/record")
    public List<BookEntity> queryRecord(
            @RequestParam(name="cardid") Integer cardId
    ) throws Exception{
        if(!cardModel.existCard(cardId)) throw new Exception("Invalid Card Id");
        return recordModel.queryBook(cardId);
    }

    @PutMapping("/record")
    public CodeEntity borrowRecord(
            @RequestParam(name="cardid") Integer cardId,
            @RequestParam(name="bookid") String bookId
            ) throws Exception{
        if(!cardModel.existCard(cardId)) throw new Exception("Invalid Card Id");
        bookAtomicModel.borrowBook(bookId, cardId, (String) httpSession.getAttribute("username"));
        return new CodeEntity(0);
    }

    @DeleteMapping("/record")
    public void returnRecord(
            @RequestParam(name="cardid") Integer cardId,
            @RequestParam(name="bookid") String bookId
            ) throws Exception{
        if(!cardModel.existCard(cardId)) throw new Exception("Invalid Card Id");
        bookAtomicModel.returnBook(bookId, cardId);
    }

    @GetMapping("/card")
    public List<CardEntity> queryAllCard(){
        return cardModel.queryAll();
    }

    @PutMapping("/card")
    public CodeEntity createCard(
            @RequestParam(name="name") String name,
            @RequestParam(name="unit") String unit,
            @RequestParam(name="type") String type
    ) throws Exception{
        return new CodeEntity(cardModel.createCard(name, unit, type));
    }

    @DeleteMapping("/card")
    public void deleteCard(
            @RequestParam(name="cardid") Integer cardId
    )throws Exception{
        cardModel.deleteCard(cardId);
    }

    @GetMapping("/admin")
    public List<AdminEntity> queryAllAdmin(){
        return adminModel.queryAll();
    }

    @PutMapping("/admin")
    public CodeEntity addAdmin(
            @RequestParam(name="username") String userName,
            @RequestParam(name="password") String passWord,
            @RequestParam(name="name") String name,
            @RequestParam(name="contact") String contact
    ) throws Exception{
        adminModel.addUser(userName, passWord, name, contact);
        return new CodeEntity(0);
    }

    @DeleteMapping("/admin")
    public void deleteAdmin(
            @RequestParam(name="username") String userName
    ) throws Exception{
        adminModel.deleteUser(userName);
    }
}
