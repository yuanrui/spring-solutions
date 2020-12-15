package org.banana.authserver.controller;

import org.banana.authserver.annotation.AllowAnonymous;
import org.banana.authserver.annotation.AllowAuthenticated;
import org.banana.authserver.domain.model.UserModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.Principal;

/**
 * @author yuanrui@live.cn
 * @since 2020/10/21 14:46
 */
@Controller
public class HomeController {

    @GetMapping( value = { "/", "/index"})
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
    public String login(Model model, HttpServletRequest request) {
        String query = request.getQueryString();

        model.addAttribute("msg", "");
        if (query != null && query.equals("error")){
            model.addAttribute("msg", "用户名或密码错误");
        }

        if (query != null && query.equals("logout")){
            model.addAttribute("msg", "您已经退出系统");
        }

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
