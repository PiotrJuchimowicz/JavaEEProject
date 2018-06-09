package com.company.project.Controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {


    @GetMapping("/loginPage")
    public String loginPage(){

        return "login-page";
    }

    @RequestMapping("/access-denied")
    public String accessDenied(){

        return "access-denied";
    }


}
