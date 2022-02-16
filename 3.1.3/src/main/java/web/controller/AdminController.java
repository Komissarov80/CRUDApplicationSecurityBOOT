package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import web.model.Role;
import web.model.User;
import web.service.UserService;

import java.security.Principal;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;

    @Autowired
    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public String getUsers(ModelMap model, Principal principal, @ModelAttribute("newUser") User newUser,
                           @ModelAttribute("editUser") User editUser) {
        model.addAttribute("users", userService.getAllUser());
        User user = userService.getUser(principal.getName());
        model.addAttribute("user", user);
        return "users";
    }

    @GetMapping("/{id}")
    public String getUser(@PathVariable("id") int id, ModelMap model) {
        model.addAttribute("user", userService.getUser(id));
        return "user";
    }

    @GetMapping("/new")
    public String newUser(@ModelAttribute("newUser") User user) {
        return "new";
    }

    @PostMapping()
    @ResponseBody
    public User addUser(@ModelAttribute("newUser") User user, @ModelAttribute("my_role") String[] roles) {
        userService.addUser(user, roles);
        return userService.getUser(user.getUsername());
    }

    @GetMapping("/{id}/edit")
    public String editUser(ModelMap modelMap, @PathVariable("id") int id) {
        modelMap.addAttribute("user", userService.getUser(id));
        return "edit";
    }

    @PatchMapping("/{id}")
    @ResponseBody
    public List<User> updateUser(@ModelAttribute("editUser") User user, @PathVariable("id") int id,
                        @ModelAttribute("my_role") String[] roles) {
        userService.updateUser(id, user, roles);
        return userService.getAllUser();
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public List<User> delete(@PathVariable("id") int id) {
        userService.deleteUser(id);
        return userService.getAllUser();
    }

}
