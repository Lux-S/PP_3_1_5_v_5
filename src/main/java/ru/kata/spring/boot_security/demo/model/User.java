package ru.kata.spring.boot_security.demo.model;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Set;

@Entity
@Table(name = "users")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_user")
    @SequenceGenerator(name = "seq_user", sequenceName = "seq_user", allocationSize = 1)
    private long id;

    private String firstName;

    private String lastName;

    private Byte age;

    private String password;

    @Column(unique = true, name = "email")
    private String email;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Byte getAge() {
        return age;
    }

    public void setAge(Byte age) {
        this.age = age;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public User(String firsName, String lastName, byte age, String email, String password, Set<Role> roles) {
        this.firstName = firsName;
        this.lastName = lastName;
        this.age = age;
        this.email = email;
        this.password = password;
        this.roles = roles;
    }

    public User(long id, String firstName, String lastName, byte age, String email, String password, Set<Role> roles) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.email = email;
        this.password = password;
        this.roles = roles;
    }

    public User() {
    }

    @Fetch(FetchMode.JOIN)
    @ManyToMany
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "users_id"),
            inverseJoinColumns = @JoinColumn(name = "roles_id"))
    private Set<Role> roles;

    @Override
    public String getUsername() {
        return email;
    }

    // получаем роли для юзера
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    // проверяем, действительный ли аккаунт
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    // проверяем, не заблокирован ли аккаунт
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    // проверяем, действительный ли пароль
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    // проверяем, работает ли аккаунт
    @Override
    public boolean isEnabled() {
        return true;
    }

    public String rolesToString() {
        StringBuilder str = new StringBuilder("");

        this.roles.forEach(role -> str.append(role.getName()));

        return str.toString();
    }
}
