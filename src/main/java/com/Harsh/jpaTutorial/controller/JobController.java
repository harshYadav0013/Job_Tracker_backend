package com.Harsh.jpaTutorial.controller;

import com.Harsh.jpaTutorial.model.JobApplication;
import com.Harsh.jpaTutorial.model.Status;
import com.Harsh.jpaTutorial.model.User;
import com.Harsh.jpaTutorial.service.JobService;
import com.Harsh.jpaTutorial.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/jobs")
@CrossOrigin(origins = "http://localhost:5173") // allows React to call this
public class JobController {

    private final JobService jobService;
    private final UserService userService;

    public JobController(JobService jobService, UserService userService) {
        this.jobService = jobService;
        this.userService = userService;
    }

    private User getLoggedInUser(Authentication auth) {
        return userService.findByEmail(auth.getName());
    }


    @GetMapping
    public ResponseEntity<List<JobApplication>> listJobs(
            @RequestParam(required = false) Status status,
            Authentication auth) {

        User user = getLoggedInUser(auth);
        List<JobApplication> jobs = (status != null)
                ? jobService.getJobsByStatus(user, status)
                : jobService.getAllJobs(user);

        return ResponseEntity.ok(jobs);  // sends JSON list
    }


    @PostMapping
    public ResponseEntity<JobApplication> addJob(
            @RequestBody JobApplication job,
            Authentication auth) {

        User user = getLoggedInUser(auth);
        job.setUser(user);
        JobApplication saved = jobService.addJob(job);
        return ResponseEntity.ok(saved);       // sends saved job as JSON
    }

    @GetMapping("/{id}")
    public ResponseEntity<JobApplication> getJob(@PathVariable Long id) {
        return ResponseEntity.ok(jobService.getJobById(id));
    }


    @PutMapping("/{id}")
    public ResponseEntity<JobApplication> updateJob(
            @PathVariable Long id,
            @RequestBody JobApplication job,   // was @ModelAttribute
            Authentication auth) {

        User user = getLoggedInUser(auth);
        job.setId(id);
        job.setUser(user);
        JobApplication updated = jobService.updateJob(job);
        return ResponseEntity.ok(updated);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteJob(@PathVariable Long id) {
        jobService.deleteJob(id);
        return ResponseEntity.noContent().build(); // sends 204
    }


    @GetMapping("/stats")
    public ResponseEntity<?> getStats(Authentication auth) {
        User user = getLoggedInUser(auth);
        List<JobApplication> all = jobService.getAllJobs(user);

        long total     = all.size();
        long applied   = all.stream().filter(j -> j.getStatus() == Status.APPLIED).count();
        long interview = all.stream().filter(j -> j.getStatus() == Status.INTERVIEW).count();
        long offer     = all.stream().filter(j -> j.getStatus() == Status.OFFER).count();
        long rejected  = all.stream().filter(j -> j.getStatus() == Status.REJECTED).count();

        return ResponseEntity.ok(java.util.Map.of(
                "total",     total,
                "applied",   applied,
                "interview", interview,
                "offer",     offer,
                "rejected",  rejected
        ));
    }
}