package com.pvthach.capstone.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

@Component
public class FileUploadStorage {

    @Value("${capstone.app.upload}")
    private String UPLOAD_DIRECTORY;
    private final String UPLOAD_FARM_PREFIX = "farm_";
    private final String UPLOAD_PRODUCT_PREFIX = "product_";

    private final String UPLOAD_PROFILE_PREFIX = "profile_";
    private final String UPLOAD_VEHICLE_PREFIX = "vehicle_";

    public List<String> saveProduct(MultipartFile[] files) throws IOException {
        List<String> urls = new ArrayList<String>();
        String name = SecurityContextHolder.getContext().getAuthentication().getName();

        try{
            for (int i = 0; i< files.length; i++) {
                MultipartFile file = files[i];
                if (file != null && !file.isEmpty()) {
                    String url = UPLOAD_PRODUCT_PREFIX + name + "_"+ file.getOriginalFilename();
                    String succeedUrl = storeFile(url, file);
                    urls.add(succeedUrl);
                }

            }
            return urls;
        } catch (Exception e) {
            throw new IOException(e.getMessage());
        }
    }

    public List<String> saveFarm(MultipartFile[] files) throws IOException {
        List<String> urls = new ArrayList<String>();
        String name = SecurityContextHolder.getContext().getAuthentication().getName();

        try{
            for (int i = 0; i< files.length; i++) {
                MultipartFile file = files[i];
                if (file != null && !file.isEmpty()) {
                    String url = UPLOAD_FARM_PREFIX + name + "_"+ file.getOriginalFilename();
                    String succeedUrl = storeFile(url, file);
                    urls.add(succeedUrl);
                }

            }
            return urls;
        } catch (Exception e) {
            throw new IOException(e.getMessage());
        }
    }

    public String saveProfilePhoto(MultipartFile file) throws IOException {
        String succeedUrl = "";
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        try{
            if (file != null && !file.isEmpty()) {
                String url = UPLOAD_PROFILE_PREFIX + name + "_"+ file.getOriginalFilename();
                succeedUrl = storeFile(url, file);
            }
        } catch (Exception e) {
            throw new IOException(e.getMessage());
        }
        return succeedUrl;
    }

    public String saveVehiclePhoto(MultipartFile file) throws IOException {
        String succeedUrl = "";
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        try{
            if (file != null && !file.isEmpty()) {
                String url = UPLOAD_VEHICLE_PREFIX + name + "_"+ file.getOriginalFilename();
                succeedUrl = storeFile(url, file);
            }
        } catch (Exception e) {
            throw new IOException(e.getMessage());
        }
        return succeedUrl;
    }

    public String storeFile(String fileName, MultipartFile file) throws IOException {
        try{
            Path filePath = Paths.get(UPLOAD_DIRECTORY + "/" + fileName);
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
            return fileName;
        } catch (Exception e) {
            throw new IOException(e.getMessage());
        }
    }

    public Resource loadFileAsResource(String fileName) throws IOException{
        try {
            Path filePath = Paths.get(fileName);
            Resource resource = new UrlResource(filePath.toUri());
            if (resource.exists()) {
                return resource;
            } else {
                throw new FileNotFoundException("File not found " + fileName);
            }
        } catch (IOException ex) {
            throw new IOException("File not found " + fileName, ex);
        }
    }
}
