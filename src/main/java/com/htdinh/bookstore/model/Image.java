package com.htdinh.bookstore.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "IMAGE", schema = "bookstore")
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IMAGE_ID", nullable = false)
    private Long id;

    @Size(max = 128)
    @Column(name = "TITLE", length = 128)
    private String title;

    @Lob
    @Column(name = "URL")
    private String url;

    @Size(max = 128)
    @Column(name = "ALT_TEXT", length = 128)
    private String altText;

    @Size(max = 128)
    @Column(name = "DESCRIPTION", length = 128)
    private String description;

    @Column(name = "CRT_ID", precision = 22)
    private Long crtId;

    @Column(name = "CRT_DT", length = 128)
    private LocalDateTime crtDt;

    @Column(name = "UPDT_ID", precision = 22)
    private Long updtId;

    @Column(name = "UPDT_DT", length = 128)
    private LocalDateTime updtDt;

    @Column(name = "IS_THUMBNAIL")
    private Boolean isThumbnail;

}