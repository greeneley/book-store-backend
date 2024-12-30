package com.htdinh.bookstore.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "REFRESH_TOKEN", schema = "bookstore")
public class RefreshToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "REFESH_TOKEN_ID", nullable = false)
    private Long id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "USER_ID", nullable = false)
    private User user;

    @Size(max = 500)
    @Column(name = "VALUE", length = 500)
    private String value;

    @Size(max = 50)
    @NotNull
    @Column(name = "EXPIRE_DT", nullable = false, length = 50)
    private String expireDt;

    @Column(name = "CRT_ID")
    private Long crtId;

    @Column(name = "CRT_DT")
    private LocalDateTime crtDt;

    @Column(name = "UPDT_ID")
    private Long updtId;

    @Column(name = "UPDT_DT")
    private LocalDateTime updtDt;


    @NotNull
    @Column(name = "IS_ACTIVE", nullable = false)
    private Boolean isActive = false;

}
