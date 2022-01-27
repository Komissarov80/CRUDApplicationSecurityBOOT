package org.exampels.controllers;

import org.exampels.Services.RoleService;
import org.exampels.Services.UserServices;
import org.exampels.model.Role;
import org.exampels.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminControllers {
    @Autowired
    private UserServices userServices;

    @Autowired
    private RoleService roleService;

    // создать юзера
    @GetMapping("/user-create")
    public String addUserForm(Model model, User user) {
        model.addAttribute(user);
        model.addAttribute("allRoles", roleService.listRole());
        return "user-create";
    }

    @PostMapping("/user-create")
    public String addUser(@ModelAttribute("user") User user) {
        userServices.addUser(user);
        return "redirect:/admin/users";
    }


    // Получить всех юзеров
    @GetMapping("/users")
    public String getUsers(Model model) {
        List<User> usersList = userServices.getAllUsers();
        model.addAttribute("users", usersList);
        return "user-list";
    }


    // удалить юзера из главной таблицы
    @PostMapping("/delete/{id}")
    public String deletUser(@PathVariable("id") Long id) {
        System.out.println("in method Post delet");
        userServices.deletUser(id);
        return "redirect:/admin/users";
    }
    // удалить юзера из url строки
    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        System.out.println("in method Get delet");
        userServices.deletUser(id);
        return "redirect:/admin/users";
    }

    // изменить
    @GetMapping("/user-update/{id}")
    public String updateUserForm(@PathVariable("id") Long id, Model model) {
        User user = userServices.getUser(id);
        model.addAttribute("allRoles", roleService.listRole());
        model.addAttribute("user", user);
        return "user-update";
    }

    @PostMapping("/user-update")
    public String updateUser(User user) {
        userServices.editUser(user);
        return "redirect:/admin/users";
    }

    @GetMapping("/roles")
    public String showAllRoles(Model model) {
        List<Role> roleList = roleService.listRole();
        model.addAttribute("roleList", roleService.listRole());
        return "roles";
    }
}
