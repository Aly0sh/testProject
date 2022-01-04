package com.example.testProject.controllers;

import com.example.testProject.models.Users;
import com.example.testProject.repositories.usersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Date;

@Controller
public class mainController {

    @Autowired
    private usersRepo usersRepo;

    @GetMapping("/")
    String home(Model model){
        model.addAttribute("users", usersRepo.findAll());
        return "index";
    }

    @GetMapping("/add-user")
    String addUser(Model model){
        return "addUser";
    }

    @PostMapping("/add-user")
    String AddUser(@RequestParam String fname,
                   @RequestParam String lname,
                   @RequestParam Date birthday,
                   @RequestParam String login,
                   @RequestParam String password,
                   @RequestParam String about_me,
                   @RequestParam String address){
        usersRepo.save(new Users(fname, lname, birthday, login, password, about_me, address));
        return "redirect:/";
    }

    @GetMapping("/redact-user/{id}")
    String redactUser(Model model, @PathVariable(name = "id") Long id){
        Users user = usersRepo.findById(id).get();
        model.addAttribute("user", user);
        return "redact";
    }

    @PostMapping("/redact-user/{id}")
    String redUser(@RequestParam String fname,
                   @RequestParam String lname,
                   @RequestParam Date birthday,
                   @RequestParam String login,
                   @RequestParam String password,
                   @RequestParam String about_me,
                   @RequestParam String address,
                   @PathVariable(name = "id") Long id){
        Users user = usersRepo.findById(id).get();
        user.setFname(fname);
        user.setLname(lname);
        user.setBirthday(birthday);
        user.setLogin(login);
        user.setPassword(password);
        user.setAbout_me(about_me);
        user.setAddress(address);
        usersRepo.save(user);
        return "redirect:/user/" + user.getId();
    }

    @GetMapping("/user/{id}")
    String userDetails(Model model, @PathVariable(name = "id") Long id){
        Users user = usersRepo.findById(id).get();
        model.addAttribute("user", user);
        return "userDetails";
    }


    @PostMapping("/user-delete/{id}")
    String userDelete(@PathVariable(name = "id") Long id){
        usersRepo.delete(usersRepo.findById(id).get());
        return "redirect:/";
    }
}
