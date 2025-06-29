package com.sasibhumaraju.job_portal.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.sasibhumaraju.job_portal.user.AppUser;
import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class WorkExperience {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @ManyToOne
    @JoinColumn(name = "appUser")
    @JsonBackReference
    private AppUser appUser;

    private String companyName;
    private String yearsWorked;
    private String designation;

    @Column(columnDefinition = "TEXT")
    private String comment;

}
