package com.asn2.asn2.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.asn2.asn2.models.User;
import com.asn2.asn2.models.UserRepository;

import jakarta.servlet.http.HttpServletResponse;

@Controller
public class UsersController {

    @Autowired
    private UserRepository userRepo;
    @GetMapping("/users/view")
    public String getAllUsers(Model model){

        System.out.println("GET all users");
        //get all users from database
        List<User> users = userRepo.findAll();
      
        //end of database call

        model.addAttribute("us", users);
        return "users/showAll";
    }

    @PostMapping("/users/addstudent")
    public String redirect(Model model){
        System.out.println("redirect to add students page");
        return "users/add";
    }

    //endpoint for submitting new students to the database
    @PostMapping("/users/adding")
    public String addUser(@RequestParam Map<String, String> newuser, HttpServletResponse response){
        System.out.println("ADD user");

        String newName = newuser.get("name");
        String newPwd = newuser.get("password");
        String newEmail = newuser.get("email");
        String newhaircol = newuser.get("haircolor");
        int newWeight = Integer.parseInt(newuser.get("weight"));
        int newHeight = Integer.parseInt(newuser.get("height"));
        int newGpa = Integer.parseInt(newuser.get("gpa"));
        userRepo.save(new User(newName, newPwd, newEmail, newhaircol, newWeight, newHeight, newGpa));
        response.setStatus(201);
        return "/users/addedUser";
    }

    //return to student view 
    @PostMapping("/users/back")
    public String backBtn(Model model){
        System.out.println("Back to student view page");

        return "redirect:/users/view";
    }

    // update student information in database
    @PutMapping("/users/edit")
    public String editUser(@PathVariable int id, @RequestParam Map<String, String> currentuser){
        User updateuser = userRepo.findById(id).get();
        updateuser.setName(currentuser.get("name"));
        userRepo.save(updateuser);
        return "/users/view";

    }
  

    //delete student from database
    @DeleteMapping("/users/delete/{uid}")
    public String deleteUser(Model model, @PathVariable String uid){
        System.out.println("DELETE user");
        int id = Integer.parseInt(uid);
        User student = userRepo.findById(id).get();
        userRepo.delete(student);
        return "/users/deletedUser";
    }

}
