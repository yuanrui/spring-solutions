package org.banana.authserver.controller;

import org.banana.authserver.annotation.AllowAnonymous;
import org.banana.authserver.annotation.AllowAuth;
import org.banana.authserver.domain.LoginModel;
import org.banana.authserver.domain.UserModel;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

/**
 * @author yuanrui@live.cn
 * @since 2020/10/21 14:46
 */
@Controller
public class HomeController {

    @GetMapping("/")
    public String index(Model model, HttpSession session) {
        model.addAttribute("test", "123456");

        UserModel userModel = (UserModel) session.getAttribute(UserModel.USER_SESSION);
        String user = "anonymous";
        if(userModel != null){
            user = userModel.getName();
        }
        model.addAttribute("user", user);

        return "index";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/login")
    public String login(LoginModel loginModel, Model model, HttpSession session) {
        if(!loginModel.getName().isEmpty()  && !loginModel.getPassword().isEmpty()){
            UserModel userModel = new UserModel();
            userModel.setName(loginModel.getName());
            userModel.setRealName("管理员");

            session.setAttribute(UserModel.USER_SESSION, userModel);
            return "redirect:/";
        }

        return "login";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }

    @AllowAuth
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
}
