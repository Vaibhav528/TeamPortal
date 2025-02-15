package com.vaibhav.binding;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data  // ✅ This automatically generates getters and setters using Lombok
public class LoginForm {

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;

    @NotBlank(message = "Password is required")
    private String password;  // ✅ Ensure the field name matches exactly

}
