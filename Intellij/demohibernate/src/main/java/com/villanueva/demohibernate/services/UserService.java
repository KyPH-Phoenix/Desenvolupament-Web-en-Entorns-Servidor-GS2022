package com.villanueva.demohibernate.services;

import com.villanueva.demohibernate.models.Film;
import com.villanueva.demohibernate.models.Job;
import com.villanueva.demohibernate.models.Student;
import com.villanueva.demohibernate.models.User;
import com.villanueva.demohibernate.repos.FilmRepo;
import com.villanueva.demohibernate.repos.JobRepo;
import com.villanueva.demohibernate.repos.StudentsRepo;
import com.villanueva.demohibernate.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    UserRepo userRepo;


    @Autowired
    JobRepo jobRepo;


    @Autowired
    FilmRepo filmRepo;
    @Autowired
    StudentsRepo studentsRepo;
    public List<User> getUsers(){
        return userRepo.findAll();
    }

    public void save(User u) {
        userRepo.save(u);
    }

    public List<Job> allJobs() {
       return jobRepo.findAll();
    }

    public List<User> findUserNameStarts(String a) {
        return userRepo.findByNameStartingWith(a);
    }

    public List<User> findUserByJob(String s){
        return userRepo.findByJob_DescriptionContains(s);
    }

    public List<Student> getAllStudents(){
        return studentsRepo.findAll();
    }
    public List<Student> findByNameLike(String s) {
        return studentsRepo.findByNameLike(s);
    }

    public List<Film> allFilms() {
        return filmRepo.findAll();
    }
}
