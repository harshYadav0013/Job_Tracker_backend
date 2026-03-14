package com.Harsh.jpaTutorial.model;

import jakarta.persistence.*;

import java.time.LocalDate;


    @Entity
    @Table(name = "job_applications")
    public class JobApplication {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private String companyName;
        private String role;
        private String location;
        private LocalDate appliedDate;

        @Enumerated(EnumType.STRING)
        private Status status;

        @Column(length = 1000)
        private String notes;

        @ManyToOne
        @JoinColumn(name = "user_id", nullable = false)
        private User user;


        public JobApplication() {}


        public Long getId() { return id; }
        public void setId(Long id) { this.id = id; }

        public String getCompanyName() { return companyName; }
        public void setCompanyName(String companyName) { this.companyName = companyName; }

        public String getRole() { return role; }
        public void setRole(String role) { this.role = role; }

        public String getLocation() { return location; }
        public void setLocation(String location) { this.location = location; }

        public LocalDate getAppliedDate() { return appliedDate; }
        public void setAppliedDate(LocalDate appliedDate) { this.appliedDate = appliedDate; }

        public Status getStatus() { return status; }
        public void setStatus(Status status) { this.status = status; }

        public String getNotes() { return notes; }
        public void setNotes(String notes) { this.notes = notes; }

        public User getUser() { return user; }
        public void setUser(User user) { this.user = user; }
    }

