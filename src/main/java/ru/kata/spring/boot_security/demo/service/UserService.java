package ru.kata.spring.boot_security.demo.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService extends UserDetailsService {

    void saveUser(User user);

    User findUserById(long id);

    List<User> getAllUsers();

    void updateUser(User updatedUser, long id);

    void deleteUser(long id);

    Optional<User> findByEmail(String email);
}
