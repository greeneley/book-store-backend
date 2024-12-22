package com.htdinh.bookstore.service;

import org.springframework.web.multipart.MultipartFile;

public interface UploadService {
    String uploadProfile(MultipartFile multipartFile, String directory) throws Exception;
}
