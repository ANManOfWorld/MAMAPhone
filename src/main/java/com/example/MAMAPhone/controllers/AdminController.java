package com.example.MAMAPhone.controllers;

import com.example.MAMAPhone.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor

@PreAuthorize("hasAuthority('ROLE_ADMIN')")
public class AdminController {
    private final UserService userService;

    @GetMapping("/admin")
    public String admin(Model model) {
        model.addAttribute("users", userService.list());
        return "admin";
    }

    @PostMapping("/user/ban/{id}")
    public String userBan(@PathVariable("id") Long id) {
        userService.userBan(id);
        return "redirect:/admin";
    }

    @PostMapping("/admin/user/edit/moderator/{id}")
    public String changeRoleModerator(@PathVariable("id") Long id) {
        userService.moderator(id);
        return "redirect:/admin";
    }

    @PostMapping("/admin/user/edit/administrator/{id}")
    public String changeRoleAdministrator(@PathVariable("id") Long id) {
        userService.administrator(id);
        return "redirect:/admin";
    }
}
