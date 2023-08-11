package ru.kata.spring.boot_security.demo.runner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.util.HashSet;
import java.util.Set;

@Component
public class CommandLineRunnerImpl implements CommandLineRunner {
    private final UserService userService;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public CommandLineRunnerImpl(UserService userService, RoleService roleService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {
        Role adminRole = new Role();
        adminRole.setName("ADMIN");
        roleService.saveRole(adminRole);

        Role userRole = new Role();
        userRole.setName("USER");
        roleService.saveRole(userRole);

        User adminUser = new User();
        adminUser.setFirstName("Admin");
        adminUser.setLastName("Adminn");
        adminUser.setAge((byte) 25);
        adminUser.setEmail("a@a.ru");
        adminUser.setPassword("admin");

        Set<Role> adminRoles = new HashSet<>();
        adminRoles.add(adminRole);
        adminUser.setRoles(adminRoles);
        userService.saveUser(adminUser);

        User regularUser = new User();
        regularUser.setFirstName("User");
        regularUser.setLastName("Userr");
        regularUser.setAge((byte) 30);
        regularUser.setEmail("u@u.ru");
        regularUser.setPassword("user");

        Set<Role> userRoles = new HashSet<>();
        userRoles.add(userRole);
        regularUser.setRoles(userRoles);
        userService.saveUser(regularUser);
    }
}
