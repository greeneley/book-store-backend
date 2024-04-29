package com.htdinh.bookstore.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "authorities")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Authorities {


    @Id
    @Size(max = 255)
    @Column(name = "AUTHORITY", nullable = false)
    private String authority;

    @OneToMany(mappedBy = "authority")
    private Set<User> users = new LinkedHashSet<>();

}
