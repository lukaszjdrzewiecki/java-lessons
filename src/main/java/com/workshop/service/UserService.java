package com.workshop.service;

import com.workshop.config.security.dto.RegisterForm;
import com.workshop.config.security.entity.Role;
import com.workshop.config.security.entity.User;
import com.workshop.config.security.enums.Roles;
import com.workshop.config.security.repository.RoleRepository;
import com.workshop.config.security.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final ApplicationContext applicationContext;
    private final RoleRepository roleRepository;
    private final MessagesService messages;

    public Optional<User> findByUserNameAndPlainTextPassword(String userName, String plainTextPassword) {

        final PasswordEncoder passwordEncoder = applicationContext.getBean(PasswordEncoder.class);

        return userRepository.findByUser(userName)
                .filter(user -> passwordEncoder.matches(plainTextPassword, user.getPassword()));
    }

    public User getUserByUserName(String userName) {
        return userRepository.findByUser(userName)
                .orElseThrow(() -> new EntityNotFoundException(String.format("User %s not found", userName)));
    }

    public User createNewUser(RegisterForm registerForm) {
        if (userRepository.existsByUserName(registerForm.getUserName())) {
            throw new IllegalArgumentException(messages.getMessage("user.exists.error"));
        }
        if (!registerForm.getPassword().equals(registerForm.getRepeatedPassword())) {
            throw new IllegalArgumentException(messages.getMessage("user.passwordRepeat.error"));
        }
        User newUser = assembleNewUserData(registerForm);
        return userRepository.save(newUser);

    }

    private User assembleNewUserData(RegisterForm registerForm) {
        final PasswordEncoder passwordEncoder = applicationContext.getBean(PasswordEncoder.class);
        String encryptedPassword = passwordEncoder.encode(registerForm.getPassword());

        Role userRole = roleRepository.findByRoleName(Roles.CUSTOMER.toString())
                .orElseThrow(() -> new EntityNotFoundException(String.format("Role %s not found", Roles.CUSTOMER)));

        return User.builder()
                .userName(registerForm.getUserName())
                .email(registerForm.getEmail())
                .roles(Set.of(userRole))
                .password(encryptedPassword)
                .build();
    }
}
