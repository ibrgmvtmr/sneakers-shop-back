package edu.alatoo.sneakers.payload;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.Set;


@Data
public class SignupRequestDTO {

    private Long id;

    @NotBlank(message = "Имя не может быть пустым")
    private String firstName;

    @NotBlank(message = "Фамилия не может быть пустой")
    private String lastName;

    @NotBlank(message = "Имя пользователя не должно быть пустым")
    private String username;

    @Email
    @NotBlank(message = "Электронная почта не может быть пустой")
    private String email;

    @NotBlank(message = "Пароль не должен быть пустым")
    private String password;

    private Set<String> roles;
}
