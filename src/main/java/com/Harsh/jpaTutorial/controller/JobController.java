package com.Harsh.jpaTutorial.controller;


import com.Harsh.jpaTutorial.model.JobApplication;
import com.Harsh.jpaTutorial.model.Status;
import com.Harsh.jpaTutorial.model.User;
import com.Harsh.jpaTutorial.service.JobService;
import com.Harsh.jpaTutorial.service.UserService;
import org.springframework.ui.Model;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/jobs")
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
    public String listJobs(@RequestParam(required = false) Status status,
                           Authentication auth, Model model) {
        User user = getLoggedInUser(auth);

        List<JobApplication> jobs;
        if (status != null) {
            jobs = jobService.getJobsByStatus(user, status);
        } else {
            jobs = jobService.getAllJobs(user);
        }

        model.addAttribute("jobs", jobs);
        model.addAttribute("statuses", Status.values());
        model.addAttribute("selectedStatus", status);
        return "jobs/list";
    }


    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("job", new JobApplication());
        model.addAttribute("statuses", Status.values());
        return "jobs/add";
    }


    @PostMapping("/add")
    public String addJob(@ModelAttribute JobApplication job,
                         Authentication auth) {
        User user = getLoggedInUser(auth);
        job.setUser(user);
        jobService.addJob(job);
        return "redirect:/jobs";
    }


    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        model.addAttribute("job", jobService.getJobById(id));
        model.addAttribute("statuses", Status.values());
        return "jobs/edit";
    }

    @PostMapping("/edit/{id}")
    public String updateJob(@PathVariable Long id,
                            @ModelAttribute JobApplication job,
                            Authentication auth) {
        User user = getLoggedInUser(auth);
        job.setId(id);
        job.setUser(user);
        jobService.updateJob(job);
        return "redirect:/jobs";
    }


    @PostMapping("/delete/{id}")
    public String deleteJob(@PathVariable Long id) {
        jobService.deleteJob(id);
        return "redirect:/jobs";
    }
}
