package com.acountservice2.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Collection;
import java.util.Set;


@Data
@NoArgsConstructor
@Entity
@Table(name = "user", schema = "AccountDB")
public class User implements UserDetails {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name", nullable = true, length = 30)
    private String firstName;
    @Column(name = "last_name", nullable = true, length = 30)
    private String lastName;
    @Column(name = "login", nullable = false, length = 30)
    private String login;
    @Column(name = "password", nullable = false, length = 30)
    private String password;
    @Column(name = "email", nullable = true, length = 40)
    private String email;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "user_spending", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "spending_id", referencedColumnName = "id"))
    Set<Spending> spendings;




    public User(String firstName, String lastName, String login, String password, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.login = login;
        this.password = password;
        this.email = email;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"));

    }

    @Override
    public String getUsername() {
        return login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }


}
