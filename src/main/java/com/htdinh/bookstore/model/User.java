package com.htdinh.bookstore.model;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User implements UserDetails{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ID", nullable = false)
    private Integer id;
    
    @Size(max = 255)
    @NotNull
    @Column(name = "PASSWORD", nullable = false)
    private String password;

    @NotNull
    @Column(name = "ENABLED", nullable = false)
    private Boolean enabled = false;
    
    @Size(max = 500)
    @Column(name = "EMAIL", length = 500)
    private String email;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "AUTHORITY_ID")
    private Authorities authority;

    @Size(max = 255)
    @NotNull
    @Column(name = "USERNAME", nullable = false)
    private String username;

    @Size(max = 150)
    @NotNull
    @Column(name = "FIRSTNAME", nullable = false, length = 150)
    private String firstname;

    @Size(max = 150)
    @NotNull
    @Column(name = "LASTNAME", nullable = false, length = 150)
    private String lastname;

    @Lob
    @Column(name = "AVATAR")
    private String avatar;

    @Size(max = 50)
    @NotNull
    @Column(name = "PHONE_NUMBER", nullable = false, length = 50)
    private String phoneNumber;

    @Size(max = 50)
    @NotNull
    @Column(name = "CREATED_AT", nullable = false, length = 50)
    private String createdAt;

//    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
//    private Cart cart;
    
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(this.authority.getName()));
        return authorities;
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
