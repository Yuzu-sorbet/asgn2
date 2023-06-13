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
import org.springframework.web.servlet.ModelAndView;

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

    //endpoint for opening up the add student input form
    @PostMapping("/users/addstudent")
    public String redirectAdd(Model model){
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
        float newGpa = Float.parseFloat(newuser.get("gpa"));
        userRepo.save(new User(newName, newPwd, newEmail, newhaircol, newWeight, newHeight, newGpa));
        response.setStatus(201);
        return "users/addedUser";
    }

    //return to main page from input form
    @PostMapping("/users/back")
    public String backButton(Model model){
        System.out.println("Back to student view page");
        return "redirect:/users/view";
    }
    
    //see visual representation of student based on height/weight  
    @PostMapping("/users/detail")
    public String studentDetail(Model model, @RequestParam Map<String, String> curstudent ){
        System.out.println("Go to detailed student view.");
        int id = Integer.parseInt(curstudent.get("id"));
        User student = userRepo.findById(id).get();
        model.addAttribute("userinfo", student);
        return "users/info";
    }

    // open up student info in edit view and allow user to edit all fields except uid
    @GetMapping("/users/updating/{uid}")
    public String updatePage(Model model, @PathVariable String uid){
        //get user-inputted uid 
        int id = Integer.parseInt(uid);
        //retrieve student info from database and display in input boxes
        User updateuser = userRepo.findById(id).get();
        model.addAttribute("update", updateuser);
        return "users/update";
    }

    /* 
     @GetMapping("/users/updating/{uid}")
    public ModelAndView updatePage(@PathVariable String uid){
        //get user-inputted uid 
        int id = Integer.parseInt(uid);
        //retrieve student info from database and display in input boxes
        ModelAndView editView = new ModelAndView("/users/update");
        User updateuser = userRepo.findById(id).get();
        editView.addObject("update", updateuser);
        return editView;
    }
    */

    //save edited student info and return to main page with updates
    @PostMapping("/users/save")
    public String saveStudentinfo(@ModelAttribute("update") User user){
        userRepo.save(user);
        return "redirect:/users/view";
    }
    
    //delete student from database
    @DeleteMapping("/users/delete/{uid}")
    public String deleteUser(@PathVariable String uid){
        
        System.out.println("DELETE user");
        int id = Integer.parseInt(uid);
        User student = userRepo.findById(id).get();
        userRepo.delete(student);
        return "users/deletedUser";
    }

}
