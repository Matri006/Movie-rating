package com.example.Good.films.services;

import com.example.Good.films.models.User;
import com.example.Good.films.repositories.UserRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class UserService implements UserDetailsService {
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }
    @Override
    public UserDetails loadUserByUsername(String username){
        User user = userRepository.findByUsername(username);
        if(user == null){
            throw new UsernameNotFoundException("Пользлователь не найден");
        }
        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .roles(user.getRoles())
                .build();
    }
    @Transactional
    public void registerUser(String username, String email, String password, String roles){
        if(userRepository.findByUsername(username) != null){
            throw new UsernameNotFoundException("Пользователь с таким именем уже существует");
        }
        User user = new User();
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));
        user.setRoles(roles);
        userRepository.save(user);
    }
    public User updateUser(String username, String password, String repeatPassword){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new UsernameNotFoundException("Пользователь не аутентифицирован");
        }

        if (!authentication.getName().equals(username)) {
            throw new AccessDeniedException("Вы можете изменять только свой профиль");
        }

        if (!password.equals(repeatPassword)) {
            throw new IllegalArgumentException("Пароли не совпадают");
        }

        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("Пользователь не найден");
        }

        user.setPassword(passwordEncoder.encode(password));
        return userRepository.save(user);

    }


}
