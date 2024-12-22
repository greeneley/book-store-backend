package com.htdinh.bookstore.service.impl;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.htdinh.bookstore.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

@Service
public class UploadServiceImpl implements UploadService {
    @Autowired
    private AmazonS3 s3Client;

    @Value("${aws.bucket-name}")
    private String bucketName;

    @Value("${aws.region}")
    private String regionName;

    @Override
    public String uploadProfile(MultipartFile multipartFile, String directory) throws Exception {
        try {
            File file = convertMultipartToFile(multipartFile);
            String fileName = UUID.randomUUID() + ".jpg"; // Default extension
            // https://book-store-images-2024.s3.ap-southeast-1.amazonaws.com/profile/image.png
            String fileUrl = "https://" + bucketName + "s3." + regionName + ".amazonaws.com/" + directory + "/" + fileName;

            uploadFileToS3Bucket(fileName, file, directory);
            file.delete();
            return fileUrl;
        } catch (Exception e) {
            throw new Exception("Upload fail: " + e.getMessage());
        }
    }

    private File convertMultipartToFile(MultipartFile file) throws IOException {
        File convFile = new File(file.getOriginalFilename());

        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(file.getBytes());
        fos.close();
        return convFile;
    }

    private void uploadFileToS3Bucket(String fileName, File file, String directory) {
        try {
            s3Client.putObject(new PutObjectRequest(bucketName + "/" + directory, fileName, file));
        } catch (AmazonServiceException ex) {
            ex.getMessage();
        }
    }
}
