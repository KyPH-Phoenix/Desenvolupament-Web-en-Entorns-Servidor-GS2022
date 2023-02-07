package com.villanueva.demohibernate.repos;

import com.villanueva.demohibernate.models.Job;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobRepo extends JpaRepository<Job, Long> {

}
