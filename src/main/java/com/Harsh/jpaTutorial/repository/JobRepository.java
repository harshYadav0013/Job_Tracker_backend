package com.Harsh.jpaTutorial.repository;

import com.Harsh.jpaTutorial.model.JobApplication;
import com.Harsh.jpaTutorial.model.Status;
import com.Harsh.jpaTutorial.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JobRepository extends JpaRepository<JobApplication,Long> {

    List<JobApplication> findByUser(User user);


    List<JobApplication> findByUserAndStatus(User user, Status status);
    long countByUser(User user);
    long countByUserAndStatus(User user, Status status);

}
