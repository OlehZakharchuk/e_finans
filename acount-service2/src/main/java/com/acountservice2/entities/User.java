package com.acountservice2.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;

import java.util.*;


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
    @Column(name = "month_report")
    private boolean monthReport;
    @Column(name = "weekly_report")
    private boolean weeklyReport;
    @Column(name = "reset_token")
    private String resetToken;

    public User(String firstName, String lastName, String login, String password, String email, boolean monthReport, boolean weeklyReport) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.login = login;
        this.password = password;
        this.email = email;
        this.monthReport = monthReport;
        this.weeklyReport = weeklyReport;
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

    public User() {
    }


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

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
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

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public boolean isMonthReport() {
        return monthReport;
    }

    public void setMonthReport(boolean monthReport) {
        this.monthReport = monthReport;
    }

    public boolean isWeeklyReport() {
        return weeklyReport;
    }

    public void setWeeklyReport(boolean weeklyReport) {
        this.weeklyReport = weeklyReport;
    }

    public String getResetToken() {
        return resetToken;
    }

    public void setResetToken(String resetToken) {
        this.resetToken = resetToken;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
