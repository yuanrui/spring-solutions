package org.banana.authserver.controller;

import org.banana.authserver.annotation.AllowAnonymous;
import org.banana.authserver.annotation.AllowAuthenticated;
import org.banana.authserver.domain.LoginModel;
import org.banana.authserver.domain.UserModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import java.security.Principal;

/**
 * @author yuanrui@live.cn
 * @since 2020/10/21 14:46
 */
@Controller
public class HomeController {

    @GetMapping("/index")
    public String index(Model model, HttpSession session, Principal principal) {
        model.addAttribute("test", "123456");

        UserModel userModel = (UserModel) session.getAttribute(UserModel.USER_SESSION);
        String user = "anonymous";
        if(principal != null){
            user = principal.getName();
            System.out.println(user);
        }
        model.addAttribute("user", user);

        return "index";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

//    @PostMapping("/login")
//    public String login(LoginModel loginModel, Model model, HttpSession session) {
//        if(!loginModel.getUsername().isEmpty()  && !loginModel.getPassword().isEmpty()){
//            UserModel userModel = new UserModel();
//            userModel.setName(loginModel.getUsername());
//            userModel.setRealName("管理员");
//
//            session.setAttribute(UserModel.USER_SESSION, userModel);
//            return "redirect:/";
//        }
//
//        return "login";
//    }
//
//    @GetMapping("/logout")
//    public String logout(HttpSession session) {
//        session.invalidate();
//        return "redirect:/login";
//    }

    @AllowAuthenticated
    @GetMapping("/hello")
    public String hello(Model model, HttpSession session){
        UserModel userModel = (UserModel) session.getAttribute(UserModel.USER_SESSION);
        model.addAttribute("user", userModel.getName());

        return "hello";
    }

    @AllowAnonymous
    @GetMapping("/test")
    public String test(){
        return "test";
    }

    @AllowAnonymous
    @GetMapping("/area/index")
    public String areaIndex(){
        return "area/index";
    }
}
