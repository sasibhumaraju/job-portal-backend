package com.sasibhumaraju.job_portal.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.sasibhumaraju.job_portal.user.AppUser;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class JobPosting {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @ManyToOne
    @JoinColumn(name = "employer")
    @JsonBackReference
    private AppUser employer;

    @OneToMany( mappedBy = "jobPosting", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Apply> applies;

    private String companyName;
    private String designation;

    @Column(columnDefinition = "TEXT")
    private String comment;

    @Column(nullable = true, columnDefinition = "TEXT" )
    private String jobLink;

}
