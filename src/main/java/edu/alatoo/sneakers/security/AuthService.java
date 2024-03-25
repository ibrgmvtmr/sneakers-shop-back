package edu.alatoo.sneakers.security;

import edu.alatoo.sneakers.exception.NotFoundException;
import edu.alatoo.sneakers.exception.UsernameAlreadyExistException;
import edu.alatoo.sneakers.model.Role;
import edu.alatoo.sneakers.model.User;
import edu.alatoo.sneakers.model.enums.Roles;
import edu.alatoo.sneakers.payload.LoginRequestDTO;
import edu.alatoo.sneakers.payload.SignupRequestDTO;
import edu.alatoo.sneakers.repository.RoleRepository;
import edu.alatoo.sneakers.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class AuthService {

    private final RoleRepository roleRepository;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    PasswordEncoder encoder;
    private final UserRepository userRepository;

    public AuthService(RoleRepository roleRepository, UserRepository userRepository) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
    }

    public void register(SignupRequestDTO userRequestDTO) {
        if (userRepository.existsByUsername(userRequestDTO.getUsername())) {
            throw  new UsernameAlreadyExistException("Такое имя пользователя уже существует");
        }

        if (userRepository.existsByEmail(userRequestDTO.getEmail())) {
            throw  new UsernameAlreadyExistException("Такая электронная почта уже существует");
        }

        User user = new User(userRequestDTO.getId(), userRequestDTO.getFirstName(), userRequestDTO.getLastName(), userRequestDTO.getUsername(),
                userRequestDTO.getEmail(),
                encoder.encode(userRequestDTO.getPassword()));

        Set<String> strRoles = userRequestDTO.getRoles();
        Set<Role> roles = new HashSet<>();


        if (strRoles == null) {
            Role userRole = roleRepository.findByName(Roles.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin" -> {
                        Role adminRole = roleRepository.findByName(Roles.ROLE_ADMIN)
                                .orElseThrow(() -> new NotFoundException("Роль не найдена"));
                        roles.add(adminRole);
                    }
                    default -> {
                        Role userRole = roleRepository.findByName(Roles.ROLE_USER)
                                .orElseThrow(() -> new NotFoundException("Роль не найдена"));
                        roles.add(userRole);
                    }
                }
            });
        }
        user.setRoles(roles);
        userRepository.save(user);
    }

    public void authenticate(LoginRequestDTO loginRequestDTO) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequestDTO.getUsername(), loginRequestDTO.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
}
