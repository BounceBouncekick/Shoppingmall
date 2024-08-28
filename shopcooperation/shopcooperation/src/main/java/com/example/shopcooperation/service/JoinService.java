package com.example.shopcooperation.service;

import com.example.shopcooperation.dto.UserDTO;
import com.example.shopcooperation.entity.UserEntity;
import com.example.shopcooperation.repository.JoinRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JoinService {

    public final JoinRepository joinRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public void save(UserDTO userDTO){
        String username = userDTO.getUsername();
        String password = userDTO.getPassword();
        String email = userDTO.getEmail();
        int age = userDTO.getAge();

        UserEntity join = UserEntity.builder()
                .username(username)
                .password(bCryptPasswordEncoder.encode(password))
                .email(email)
                .age(age)
                .role("ROLE_ADMIN")
                .build();

        joinRepository.save(join);
    }
}
