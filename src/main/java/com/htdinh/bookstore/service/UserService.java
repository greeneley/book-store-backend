package com.htdinh.bookstore.service;

import com.htdinh.bookstore.dto.request.ForgotPasswordRequest;
import com.htdinh.bookstore.dto.request.ProfileUpdateRequest;
import com.htdinh.bookstore.dto.request.ResetPasswordRequest;
import com.htdinh.bookstore.dto.response.ProfileUserResponse;
import com.htdinh.bookstore.model.User;
import org.springframework.web.multipart.MultipartFile;

public interface UserService {
    String verifyUser(String code);

    void updateResetPasswordToken(String token, String email);

    User getByResetPasswordToken(String token);

    void updatePassword(User user, String newPassword);

    String processForgotPassword(ForgotPasswordRequest request);

    String processResetPassword(ResetPasswordRequest request);

    ProfileUserResponse getProfileUser();

    String uploadAvatarProfile(MultipartFile multipartFile) throws Exception;

    String updateProfileUser(ProfileUpdateRequest request);
}
