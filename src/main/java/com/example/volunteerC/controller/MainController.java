package com.example.volunteerC.controller;

import com.example.volunteerC.domain.Message;
import com.example.volunteerC.domain.User;
import com.example.volunteerC.domain.Role;
import com.example.volunteerC.repos.UserRepo;
import com.example.volunteerC.service.MessageService;
import com.example.volunteerC.service.RequestService;
import com.example.volunteerC.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.*;

@Controller
public class MainController {
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private UserService userService;

    @Autowired
    private RequestService requestService;

    @Autowired
    private MessageService messageService;


    @GetMapping("/")
    public String greeting(Map<String, Object> model) {
        return "greeting";
    }

    @GetMapping("/main")
    public String main(@RequestParam(required = false, defaultValue = "") String filter, Model model) {

        ArrayList<User> users_to_vol = requestService.FindPairs(userService.GetCurrentUser());
        Iterable<Message> messages = messageService.checkFiltersInMes(filter);
        model.addAttribute("users_to_vol", users_to_vol);
        model.addAttribute("reqID", requestService.GetReq(userService.GetCurrentUser()));
        model.addAttribute("finding", userService.GetCurrentUser().isFinding());
        model.addAttribute("messages", messages);
        model.addAttribute("users", userRepo.findAll());
        model.addAttribute("users_c", userService.getUsersByRole(Role.CLIENT));
        model.addAttribute("users_v", userService.getUsersByRole(Role.VOLUNTEER));
        model.addAttribute("filter", filter);
        return "main";
    }

    @GetMapping("/main/{user}/{c_user}")
    public String userEditForm(@PathVariable User user,@PathVariable User c_user, Model model) {
        requestService.CreateRequestAndRefresh(user, userService.GetCurrentUser());
        return "redirect:/main";
    }

    @GetMapping("/main/{reqID}")
    public String deleteReq(@PathVariable long reqID) {
        requestService.DeleteReq(reqID);
        return "redirect:/main";
    }



    @PostMapping("/main")
    public String add(
            @AuthenticationPrincipal User user,
            @RequestParam String text,
            @RequestParam String tag, Map<String, Object> model
    ) throws IOException {
        model.put("messages", messageService.CreateMessage(user,text,tag));
        return "redirect:/main";
    }

}
