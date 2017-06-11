package org.zcy.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.zcy.Entities.BookEntity;
import org.zcy.Models.BookModel;

import java.util.List;

/**
 * Created by zcy on 2017/3/6.
 */
@RestController
@RequestMapping("/query")
public class QueryController {

    BookModel bookModel;

    @Autowired
    public QueryController(BookModel bookModel) {
        this.bookModel = bookModel;
    }

    @GetMapping
    public List<BookEntity> query(
            @RequestParam(name="name", required=false) String name,
            @RequestParam(name="type", required=false) String type,
            @RequestParam(name="author", required=false) String author,
            @RequestParam(name="press", required=false) String press,
            @RequestParam(name="yearLeft", required=false) Integer yearLeft,
            @RequestParam(name="yearRight", required=false) Integer yearRight,
            @RequestParam(name="priceLeft", required=false) Double priceLeft,
            @RequestParam(name="priceRight", required=false) Double priceRight
            ){
        Integer parameterCount = 0;
        if( name != null) parameterCount ++;
        if( type != null) parameterCount ++;
        if( author != null) parameterCount ++;
        if( yearLeft != null) parameterCount ++;
        if( yearRight != null) parameterCount ++;
        if( priceLeft != null) parameterCount ++;
        if( priceRight != null) parameterCount ++;
        if( press != null) parameterCount ++;
        return bookModel.queryBook(new Object[]{
                type, name, press, author,
                priceLeft, priceRight, yearLeft, yearRight
        }, parameterCount);
    }
}
