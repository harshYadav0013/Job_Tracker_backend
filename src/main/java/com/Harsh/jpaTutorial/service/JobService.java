package com.Harsh.jpaTutorial.service;

import com.Harsh.jpaTutorial.model.JobApplication;
import com.Harsh.jpaTutorial.model.Status;
import com.Harsh.jpaTutorial.model.User;
import com.Harsh.jpaTutorial.repository.JobRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobService {

    private final JobRepository jobRepository;

    public JobService(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }


    public List<JobApplication> getAllJobs(User user) {
        return jobRepository.findByUser(user);
    }


    public List<JobApplication> getJobsByStatus(User user, Status status) {
        return jobRepository.findByUserAndStatus(user, status);
    }


    public void addJob(JobApplication job) {
        jobRepository.save(job);
    }


    public JobApplication getJobById(Long id) {
        return jobRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Job not found"));
    }


    public void updateJob(JobApplication job) {
        jobRepository.save(job);
    }


    public void deleteJob(Long id) {
        jobRepository.deleteById(id);
    }
    public long getTotalCount(User user) {
        return jobRepository.countByUser(user);
    }

    public long getCountByStatus(User user, Status status) {
        return jobRepository.countByUserAndStatus(user, status);
    }

}
