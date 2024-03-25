package edu.alatoo.sneakers.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.alatoo.sneakers.payload.LoginRequestDTO;
import edu.alatoo.sneakers.payload.SignupRequestDTO;
import edu.alatoo.sneakers.security.AuthService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AuthController.class)
public class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AuthService authService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void register_Success() throws Exception {
        SignupRequestDTO signUpRequest = new SignupRequestDTO();
        signUpRequest.setUsername("user");
        signUpRequest.setId(1L);
        signUpRequest.setFirstName("user1");
        signUpRequest.setLastName("user2");
        signUpRequest.setPassword("password");
        signUpRequest.setEmail("user@example.com");

        doNothing().when(authService).register(signUpRequest);

        mockMvc.perform(post("/api/auth/signup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(signUpRequest)))
                .andExpect(status().isOk())
                .andExpect(content().string("Пользователь успешно зарегестрирован"));
    }

    @Test
    public void authenticate_Success() throws Exception {
        LoginRequestDTO loginRequestDTO = new LoginRequestDTO();
        loginRequestDTO.setUsername("user");
        loginRequestDTO.setPassword("password");

        doNothing().when(authService).authenticate(loginRequestDTO);

        mockMvc.perform(post("/api/auth/signin")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(loginRequestDTO)))
                .andExpect(status().isOk())
                .andExpect(content().string("Добро пожаловать в систему!"));
    }
}
