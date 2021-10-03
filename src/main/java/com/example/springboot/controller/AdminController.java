package com.example.springboot.controller;

import com.example.springboot.model.Role;
import com.example.springboot.model.User;
import com.example.springboot.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.HashSet;
import java.util.Set;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;
    private final PasswordEncoder encoder;

    public AdminController(UserService userService, PasswordEncoder encoder) {
        this.userService = userService;
        this.encoder = encoder;
    }


    @GetMapping("")
    public String userList(@ModelAttribute("newUser") User newUser, Model model, Principal principal) {
        model.addAttribute("user", userService.findUserByLogin(principal.getName()));
        model.addAttribute("allRoles" , userService.getAllRoles());
        model.addAttribute("newUser", newUser);
        model.addAttribute("users", userService.listUsers());
        return "admin_panel";
    }

    @PostMapping("/create")
    public String saveUser(@ModelAttribute("user") User user,
                           @RequestParam(value = "role", required = false) String[] roles) {
        Set<Role> roleSet = new HashSet<>();
        if (roles != null) {
            for (String role : roles) {
                roleSet.add(userService.getRole(role));
            }
        }
        user.setPassword(encoder.encode(user.getPassword()));
        user.setRole(roleSet);
        userService.add(user);
        return "redirect:/admin";
    }


    @PostMapping("/edit/{id}")
    public String update(@ModelAttribute("user") User user,
                         @RequestParam(value = "role", required = false) String[] roles) {
        Set<Role> roleSet = new HashSet<>();
        if (roles != null) {
            for (String role : roles) {
                roleSet.add(userService.getRole(role));
            }
        }
        user.setRole(roleSet);
        userService.editUser(user);
        return "redirect:/admin";
    }

    //удаление пользователя
    @PostMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        userService.delete(id);
        return "redirect:/admin";
    }
}
