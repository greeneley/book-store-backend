package com.htdinh.bookstore.service;

import org.springframework.core.io.Resource;

public interface ResourceService {
    Resource loadResourceByName(String resourceName);
}
