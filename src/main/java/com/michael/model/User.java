package com.michael.model;

import com.michael.utils.AutoDate;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "users")
public class User extends AutoDate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "full_name")
    @NotEmpty(message = "Full name field is required")
    private String fullName;
    @NotEmpty(message = "Email field is required")
    @Email
    private String email;
    @NotEmpty(message = "Password field is required")
    @Size(min = 3, message = "Password field should be more than 3 characters long")
    private String password;
    private String type;

    //Shipping Details:
    private String phone;
    @Column(columnDefinition = "TEXT")
    private String address;
    private String location;

}
