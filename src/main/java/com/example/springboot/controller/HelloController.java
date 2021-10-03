package com.example.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class HelloController {

    @GetMapping(value = "/")
    public String printWelcome(Model model) {
        List<String> messages = new ArrayList<>();
        messages.add("хеллоу)");
        messages.add("ФРОООООНТ11!1!11");
        model.addAttribute("messages", messages);
        return "index";
    }

}
