package com.villanueva.demohibernate.repos;

import com.villanueva.demohibernate.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepo extends JpaRepository<User, Long> {
    List<User> findByNameStartingWith(String s);
    List<User> findByBirthYearBetween(int y1, int y2);

    List<User> findByJob_DescriptionContains(String s);
}
