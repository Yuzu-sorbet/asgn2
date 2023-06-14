package com.asn2.asn2.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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

        System.out.println("GET all students");
        // get all students from database
        List<User> users = userRepo.findAll();
      
        // add all students to be displayed
        model.addAttribute("us", users);
        return "users/showAll";
    }

    // opens up the add student input form from add student button
    @PostMapping("/users/addstudent")
    public String redirectAdd(Model model){
        System.out.println("OPEN add student page");
        return "users/add";
    }

    // saves new student attributes and student to database based on user input
    @PostMapping("/users/adding")
    public String addUser(@RequestParam Map<String, String> newuser, HttpServletResponse response){
        System.out.println("ADD student");
        
        // retrieve user input and create new student 
        String newName = newuser.get("name");
        String newPwd = newuser.get("password");
        String newEmail = newuser.get("email");
        String newhaircol = newuser.get("haircolor");
        int newWeight = Integer.parseInt(newuser.get("weight"));
        int newHeight = Integer.parseInt(newuser.get("height"));
        float newGpa = Float.parseFloat(newuser.get("gpa"));
        userRepo.save(new User(newName, newPwd, newEmail, newhaircol, newWeight, newHeight, newGpa));
        response.setStatus(201);
        return "redirect:/users/view";
    }

    // return to main page from input form when back button is pressed
    @PostMapping("/users/back")
    public String backButton(Model model){
        System.out.println("BACK to student view page");
        return "redirect:/users/view";
    }
    
    // opens up detailed display of student page based on user-inputted uid
    @PostMapping("/users/detail")
    public String studentDetail(Model model, @RequestParam Map<String, String> curstudent ){
        System.out.println("OPEN detailed student view");

        // find student in database with corresponding uid
        int id = Integer.parseInt(curstudent.get("id"));
        User student = userRepo.findById(id).get();

        // fetch student data and add to model for display
        model.addAttribute("userinfo", student);
        return "users/info";
    }

    // open up student info in edit view and allow user to edit all fields except uid
    @GetMapping("/users/updating/{uid}")
    public String updatePage(Model model, @PathVariable String uid){
        System.out.println("EDIT student");

        // find student in database with corresponding uid
        int id = Integer.parseInt(uid);

        //retrieve student info from database and display in input boxes
        User updateuser = userRepo.findById(id).get();
        model.addAttribute("update", updateuser);
        return "users/update";
    }

    // save edited student attributes and return to display page
    @PostMapping("/users/save")
    public String saveStudentinfo(@ModelAttribute("update") User user){
        System.out.println("SAVE student");
        userRepo.save(user);
        return "redirect:/users/view";
    }
    
    // delete student from database based on uid
    @DeleteMapping("/users/delete/{uid}")
    public String deleteUser(@PathVariable String uid){
        System.out.println("DELETE student");
        // find student with corresponding uid 
        int id = Integer.parseInt(uid);
        User student = userRepo.findById(id).get();

        userRepo.delete(student);
        return "redirect:/users/view";
    }

}
