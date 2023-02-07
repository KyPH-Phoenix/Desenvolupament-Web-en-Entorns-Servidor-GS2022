package com.villanueva.demohibernate.repos;

import com.villanueva.demohibernate.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StudentsRepo extends JpaRepository<Student, Long> {
    @Query(nativeQuery = true, value = "select * from student where name like :name")
    List<Student> findByNameLike(String name);
}
