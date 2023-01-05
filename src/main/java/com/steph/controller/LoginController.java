package com.steph.controller;

import com.steph.entity.UserDetails;
import com.steph.model.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {

    @Autowired
    UserService userService;


    @RequestMapping("/")
    public ModelAndView landingPageController(HttpSession session) {
        session.invalidate();
        return new ModelAndView("landing");
    }

    @RequestMapping("/logIn")
    public ModelAndView logInController() {
        return new ModelAndView("login");
    }

    @RequestMapping("/loggedIn")
    public ModelAndView loggedInController(@RequestParam("username") String username, @RequestParam("password") String password, HttpSession session) {
        ModelAndView mv = new ModelAndView();

        UserDetails user = userService.loginCheck(username, password);


        if (user == null) {
            String message = "Incorrect login details, please try again";
            mv.addObject("message", message);
            mv.setViewName("login");
        } else {
            session.setAttribute("user", user);
            mv.setViewName("homePage");
        }
        return mv;

    }

    @RequestMapping("/signUp")
    public ModelAndView signUpController() {
        return new ModelAndView("signUp");
    }

    @RequestMapping("/signedUp")
    public ModelAndView signedUpController(@ModelAttribute("newUser") UserDetails newUser, HttpSession session) {
        ModelAndView mv = new ModelAndView();

        UserDetails user = userService.createUser(newUser);

        if (user == null) {
            String message = "That username already exists. Please select a different one.";
            mv.addObject("message", message);
            mv.setViewName("signUp");
        } else {
            session.setAttribute("user", user);
            mv.setViewName("homePage");
        }
        return mv;
    }

    @RequestMapping("/homePage")
    public ModelAndView homePageController() {
        return new ModelAndView("homePage");
    }


}
