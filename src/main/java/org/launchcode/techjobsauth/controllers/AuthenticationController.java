package org.launchcode.techjobsauth.controllers;

import jakarta.servlet.http.HttpSession;
import org.launchcode.techjobsauth.models.data.User;
import org.launchcode.techjobsauth.models.data.UserRepository;
import org.launchcode.techjobsauth.models.dto.RegisterFormDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Optional;

@Controller
public class AuthenticationController {
    @Autowired
    private UserRepository userRepository;

    private static final String sessionKey = "user";

    public User getUserFromSession(HttpSession session){
        Integer userId = (Integer) session.getAttribute(sessionKey);

        if (userId == null){
            return null;
        }

        Optional<User> user = userRepository.findById(userId);

        if (user.isEmpty()){
            return null;
        }

        return user.get();
    }

    private static void setUserInSession(HttpSession session, User user){
        session.setAttribute(sessionKey, user.getId());
    }

    @GetMapping("register")
    public String displayRegistrationForm(Model model){
        model.addAttribute(new RegisterFormDTO());
        return "register";
    }
}
