package com.example.springboot.controller;

import com.example.springboot.model.User;
import com.example.springboot.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AdminController {

    private final UserService userService;

    public AdminController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping(value = "/admin")
    public String UserController(ModelMap model) {
        model.addAttribute("users", userService.listUsers());
        return "User";
    }

    @GetMapping("/admin/edit")
    public String editUser(@RequestParam(value = "id", required = false) Long id, Model model) {
        User user = userService.getUserById(id);
        model.addAttribute("user", user);
        return "editPage";
    }

    @PostMapping(value = "/admin/edit")
    public String editUser(@ModelAttribute("user") User user) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/");
        userService.editUser(user);
        return "redirect:/admin";
    }

    @GetMapping("/admin/delete")
    public String deleteUserById(@RequestParam(value = "id", required = false) Long id) {
        userService.delete(id);
        return "redirect:/admin";
    }

    @GetMapping(value = "/admin/add")
    public String addUser(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "addUser";
    }

    @PostMapping(value = "/admin/add")
    public String addUser(@ModelAttribute("user") User user) {
        userService.add(user);
        return "redirect:/admin";
    }
}
