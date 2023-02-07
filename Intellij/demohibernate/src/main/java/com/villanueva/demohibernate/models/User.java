package com.villanueva.demohibernate.models;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;

@Entity
public class User {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        Long id;

        String name;
        Integer birthYear;



        @ManyToOne
        @Nullable
        @JoinColumn(name = "job_id")
        Job job;

    public Integer getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(Integer birthYear) {
        this.birthYear = birthYear;
    }

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
