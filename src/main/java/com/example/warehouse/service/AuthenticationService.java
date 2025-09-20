package com.example.warehouse.service;



import com.example.warehouse.dto.UserDto;
import com.example.warehouse.entity.User;
import com.example.warehouse.repository.UserRepository;
import com.example.warehouse.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public String registerUser(UserDto user) {
        if (userRepository.existsByUsername(user.getUsername())) {
            throw new RuntimeException("user with this name already exist");
        }



        User users = User.builder()
                .username(user.getUsername())
                .password(passwordEncoder.encode(user.getPassword()))
                .email(user.getEmail())
                .build();

        userRepository.saveAndFlush(users);


        return jwtUtil.generateToken(user.getUsername());
    }
}