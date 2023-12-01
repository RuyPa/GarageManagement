package com.example.gara.controller;

import com.example.gara.model.Member;
import com.example.gara.service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class LoginController {
    private final UserService userService;
    @GetMapping("")
    public String loginView(Model model){
        Member member = new Member();
        model.addAttribute("user", member);
        return "LoginView";
    }

    @PostMapping("/login")
    private String login(@ModelAttribute("user")Member member,
                         HttpSession httpSession){
        Member memberFromDb= userService.getAccount(member.getUsername());
        if(memberFromDb.getUsername().equals(member.getUsername()) && memberFromDb.getPassword().equals(member.getPassword())){
            httpSession.setAttribute("employee", memberFromDb);
            return "HomeManagerView";
        }
        return "LoginView";
    }

}
