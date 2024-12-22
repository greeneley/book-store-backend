package com.htdinh.bookstore.config;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AmazonClient {

    @Value("${aws.accessKey}")
    private String accessToken;

    @Value("${aws.secretKey}")
    private String secretKey;

    @Value("${aws.region}")
    private String region;

    @Bean
    public AmazonS3 s3client() {
        BasicAWSCredentials awsCredentials = new BasicAWSCredentials(accessToken, secretKey);

        AmazonS3 s3Client = AmazonS3ClientBuilder.standard().withRegion(Regions.fromName(region)).withCredentials(new AWSStaticCredentialsProvider(awsCredentials)).build();
        return s3Client;
    }
}
