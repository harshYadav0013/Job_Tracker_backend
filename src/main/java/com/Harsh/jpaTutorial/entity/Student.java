package com.Harsh.jpaTutorial.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(
        name = "students",
        uniqueConstraints = @UniqueConstraint(
                name ="surname_unique",
                columnNames = "Surname"
        )
)
public class Student {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long studentId;
    private  String firstName;
    @Column(
            name="Surname",
            nullable = false
    )
    private  String lastName;
    private String email;
    private  String guardianName;
    private  String guardianEmail;
    private Integer guardianMobile;

}
