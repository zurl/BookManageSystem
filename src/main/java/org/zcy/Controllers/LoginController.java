package org.zcy.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.zcy.Entities.CodeEntity;
import org.zcy.Models.AdminModel;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by zcy on 2017/3/1.
 */

@RestController
@RequestMapping("/login")
public class LoginController {

    AdminModel adminModel;

    public LoginController(AdminModel adminModel) {
        this.adminModel = adminModel;
    }

    @PostMapping
    public CodeEntity login(
            @RequestParam("username")String userName,
            @RequestParam("password")String passWord,
            HttpServletRequest request
    )throws Exception{
        boolean result = adminModel.login(userName, passWord);
        if(result) request.getSession().setAttribute("username", userName);
        return new CodeEntity(result? 1: -1);
    }

    @DeleteMapping
    public void logout(HttpServletRequest request){
        request.getSession().removeAttribute("username");
    }
}
