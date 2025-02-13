package com.example.Projekt.config;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private Long id;

    @NotEmpty(message = "{user.firstName.notEmpty}")
    @Size(min = 2, message = "{user.firstName.size}")
    private String firstName;

    @NotEmpty(message = "{user.lastName.notEmpty}")
    @Size(min = 2, message = "{user.lastName.size}")
    private String lastName;

    @NotEmpty(message = "{user.email.notEmpty}")
    @Email(message = "{user.email.invalidFormat}")
    private String email;

    @NotEmpty(message = "{user.password.notEmpty}")
    @Size(min = 6, message = "{user.password.size}")
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d).+$", message = "{user.password.pattern}")
    private String password;

}
