package com.sasibhumaraju.job_portal.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.sasibhumaraju.job_portal.user.AppUser;
import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Apply {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @ManyToOne
    @JoinColumn(name = "applies")
    @JsonBackReference
    private JobPosting jobPosting;

    @ManyToOne
    @JoinColumn(name = "jobSeeker")
    @JsonBackReference
    private AppUser jobSeeker;

    private Float expectedSalary;
    private String comment;
}
