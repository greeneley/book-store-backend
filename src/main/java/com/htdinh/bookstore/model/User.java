package com.htdinh.bookstore.model;

import com.htdinh.bookstore.enums.Role;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "USER", schema = "bookstore")
public class User implements UserDetails {

    @Id
    @Column(name = "USER_ID", nullable = false, precision = 22)
    @Getter
    private Long id;

    @Size(max = 50)
    @NotNull
    @Column(name = "USERNAME", nullable = false, length = 50, unique = true)
    private String username;

    @Size(max = 255)
    @NotNull
    @Column(name = "PASSWORD", nullable = false, length = 50)
    private String password;

    @Column(name = "IS_ACTIVE", nullable = false)
    private Boolean isActive = false;

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
    @Column(name = "EMAIL", nullable = false, length = 128, unique = true)
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
    private Long crtId;

    @Column(name = "CRT_DT", length = 128)
    private LocalDateTime crtDt;

    @Column(name = "UPDT_ID", precision = 22)
    private Long updtId;

    @Column(name = "UPDT_DT", length = 128)
    private LocalDateTime updtDt;

    @Enumerated(EnumType.STRING)
    @Column(name = "ROLE")
    private Role role;

    @Size(max = 64)
    @Column(name = "VERIFICATION_CODE", length = 64)
    private String verificationCode;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(String.valueOf(this.role)));
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
        return this.isActive;
    }
}
