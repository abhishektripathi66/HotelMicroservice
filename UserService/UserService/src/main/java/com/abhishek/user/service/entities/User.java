package com.abhishek.user.service.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name="micro_users")
public class User {

    @Id
    @Column(name="ID")
    private String userID;
    @Column(name="NAME", length = 30)
    private String name;
    @Column(name="EMAIL")
    private String email;
    private  String about;
    private String phone;
    private String dob;

    @Transient
    private List<Rating> ratings= new ArrayList<>();
}
