package com.nedashota.chatsnsapp.controllers;

import com.nedashota.chatsnsapp.beans.SessionControl;
import com.nedashota.chatsnsapp.dtos.LoginForm;
import com.nedashota.chatsnsapp.dtos.SignupForm;
import com.nedashota.chatsnsapp.repositories.UserRepository;
import com.nedashota.chatsnsapp.entities.User;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@Controller
public class UserController {
    @Autowired
    UserRepository userRepository;
    @Autowired
    SessionControl sessionControl;

    private Logger logger = LoggerFactory.getLogger(UserController.class);

    @GetMapping("/")
    public String index() {
        logger.info(LocalDateTime.now().toString());
        return "index";
    }

    @GetMapping("/signup")
    public ModelAndView showSignUpPage(@ModelAttribute("signupForm") SignupForm signupForm, ModelAndView mav) {
        mav.setViewName("signup");
        mav.addObject("signupForm", signupForm);

        return mav;
    }
    @PostMapping("/signup")
    @Transactional
    public ModelAndView signup(@ModelAttribute("signupForm") SignupForm signupForm, ModelAndView mav) throws IOException {
        User existingUser = this.userRepository.findByEmail(signupForm.getMailAddress());
        if (existingUser != null) {
            mav.setViewName("signup");
            return mav;
        }
        User signupUser = signupForm.toUser();
        this.userRepository.saveAndFlush(signupUser);
        this.logger.info("sign up(" + signupUser + ")");

        mav.setViewName("redirect:/users");
        this.sessionControl.login(signupUser.toLoginForm());

        return mav;
    }
    @GetMapping("/login")
    public ModelAndView showLoginPage(@ModelAttribute("loginForm") LoginForm loginForm, ModelAndView mav) {
        mav.setViewName("login");
        return mav;
    }
    @PostMapping("/login")
    public ModelAndView login(@ModelAttribute("loginForm") LoginForm loginForm, ModelAndView mav){
        if(this.sessionControl.login(loginForm)){
            mav.setViewName("redirect:/users");
        } else {
            mav.setViewName("/login");
        }
        return mav;
    }

    @GetMapping("/users")
    public ModelAndView listUsers(ModelAndView mav) {
        mav.setViewName("users");
        User loginUser = this.sessionControl.getUser();
        mav.addObject("user", loginUser);
        List<User> peers = this.userRepository.findAllExcept(loginUser.getId());
        mav.addObject("peers", peers);

        return mav;

    }
    @GetMapping(value = "/image/{id}", produces = MediaType.IMAGE_JPEG_VALUE)
    public @ResponseBody byte[] getIcon(@PathVariable("id") Integer id) {
        byte[] icon = this.userRepository.findIconById(id);
        return icon;
    }






}
