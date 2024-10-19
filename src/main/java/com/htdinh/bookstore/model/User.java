package com.htdinh.bookstore.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "USER")
public class User implements UserDetails {

    @Id
    @Column(name = "USER_ID", nullable = false, precision = 22)
    @Getter
    private BigDecimal id;

    @Size(max = 50)
    @NotNull
    @Column(name = "USERNAME", nullable = false, length = 50)
    private String username;

    @Size(max = 255)
    @NotNull
    @Column(name = "PASSWORD", nullable = false, length = 50)
    private String password;

    @Size(max = 1)
    @NotNull
    @Column(name = "IS_ACTIVE", nullable = false, length = 1)
    private String isActive;

    @Size(max = 50)
    @NotNull
    @Column(name = "FIRST_NAME", nullable = false, length = 50)
    private String firstName;

    @Size(max = 50)
    @NotNull
    @Column(name = "LAST_NAME", nullable = false, length = 50)
    private String lastName;

    @Size(max = 50)
    @NotNull
    @Column(name = "PHONE", nullable = false, length = 50)
    private String phone;

    @Size(max = 128)
    @NotNull
    @Column(name = "EMAIL", nullable = false, length = 128)
    @Getter
    private String email;

    @Size(max = 128)
    @NotNull
    @Column(name = "BIRTH_DAY", nullable = false, length = 128)
    private String birthDay;

    @Size(max = 128)
    @NotNull
    @Column(name = "USER_TYPE", nullable = false, length = 128)
    private String userType;

    @Column(name = "CRT_ID", precision = 22)
    private BigDecimal crtId;

    @Size(max = 128)
    @Column(name = "CRT_DT", length = 128)
    private String crtDt;

    @Column(name = "UPDT_ID", precision = 22)
    private BigDecimal updtId;

    @Size(max = 128)
    @Column(name = "UPDT_DT", length = 128)
    private String updtDt;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("admin"));
        return authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
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
