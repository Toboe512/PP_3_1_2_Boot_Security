package ru.kata.spring.boot_security.demo.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AdminController(UserService userService, RoleService roleService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping()
    public String getUsers(@AuthenticationPrincipal User authenticationUser,
                           @ModelAttribute("user_edit") User user,
                           Model model) {
        model.addAttribute("user", user);
        model.addAttribute("auth_user", authenticationUser);
        model.addAttribute("users", userService.listUsers());
        return "admin";
    }

    @PostMapping()
    public String create(@RequestParam(value = "roles_string", required = false) String[] roles,
                         @ModelAttribute("user_edit") User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.add(roleService.setRolesByStrings(roles, user));
        return "redirect:/admin";
    }

    @PatchMapping()
    public String edit(
            @RequestParam(value = "roles_string", required = false) String[] roles,
            @ModelAttribute("user_edit") User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.update(roleService.setRolesByStrings(roles, user));
        return "redirect:/admin";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Long id) {
        userService.delete(userService.getUser(id));
        return "redirect:/admin";
    }
}
