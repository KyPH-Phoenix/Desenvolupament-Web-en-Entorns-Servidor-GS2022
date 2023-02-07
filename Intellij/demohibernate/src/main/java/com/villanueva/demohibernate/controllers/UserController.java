package com.villanueva.demohibernate.controllers;

import com.villanueva.demohibernate.models.Film;
import com.villanueva.demohibernate.models.Job;
import com.villanueva.demohibernate.models.Student;
import com.villanueva.demohibernate.models.User;
import com.villanueva.demohibernate.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
@Controller
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/users")
    public String getUser(Model m){
        List<User> user = userService.findUserByJob("Pro");
        m.addAttribute("users", user);
        return "users";
    }


    @GetMapping("/newuser")
    public String newUserGet(Model m){
        List<Job> jobs = userService.allJobs();
        m.addAttribute("jobs", jobs);
        return "newUser";
    }
    @PostMapping("/newuser")
    public String newUserPost(Model m, User u){
        userService.save(u);
        m.addAttribute("ok", true);
        return "newUser";
    }

    @GetMapping("/students")
    public String getStudents(Model m){
        List<Student> students = userService.getAllStudents();
        m.addAttribute("students", students);
        return "students";
    }

    @GetMapping("/studentsLike/{name}")
    public String getStudents(Model m, @PathVariable String name){
        List<Student> students = userService.findByNameLike("%" + name + "%");
        m.addAttribute("students", students);
        return "students";
    }

    @GetMapping("/film")
    public String getFilms(Model m){
        List<Film> films = userService.allFilms();
        m.addAttribute("films", films);
        return "films";
    }
}
