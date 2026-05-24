package com.htdinh.bookstore.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReviewResponse {

    private Long id;
    private Integer rating;
    private String title;
    private String body;
    private Boolean verifiedPurchase;
    private LocalDateTime crtDt;
    private ReviewUserDto user;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ReviewUserDto {
        private Long id;
        private String username;
        private String firstName;
        private String lastName;
        private String photos;
    }
}

