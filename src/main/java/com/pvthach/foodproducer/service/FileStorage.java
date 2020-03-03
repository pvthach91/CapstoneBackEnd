//package com.pvthach.foodproducer.service;
//
//import org.apache.commons.io.FilenameUtils;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.core.io.Resource;
//import org.springframework.core.io.UrlResource;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.stereotype.Component;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.io.FileNotFoundException;
//import java.io.IOException;
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.nio.file.Paths;
//import java.nio.file.StandardCopyOption;
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//
//@Component
//public class FileStorage {
//
//    @Value("${foodproducer.app.upload}")
//    private String UPLOAD_DIRECTORY;
//    private final String UPLOAD_BRAND_PREFIX = "brand_";
//    private final String UPLOAD_MOTORCYCLE_PREFIX = "motorcycle_";
//
//    public List<String> saveMotorcyclePhotos(MultipartFile[] files) throws IOException {
//        List<String> urls = new ArrayList<String>();
//
//        String username = SecurityContextHolder.getContext().getAuthentication().getName();
//        String nameWithDate = UPLOAD_MOTORCYCLE_PREFIX + username+ "_" + generateNameByCurrentDateTime();
//        try{
//            for (int i = 0; i< files.length; i++) {
//                MultipartFile file = files[i];
//                if (file != null && !file.isEmpty()) {
//                    // Will check username login
//                    String url = nameWithDate + "_" + (i+1) + "." + FilenameUtils.getExtension(file.getOriginalFilename());
//                    String succeedUrl = storeFile(url, file);
//                    urls.add(succeedUrl);
//                }
//
//            }
//            return urls;
//        } catch (Exception e) {
//            throw new IOException(e.getMessage());
//        }
//    }
//
//    public String saveBrandLogo(MultipartFile file) throws IOException {
//        String url = "";
//        try{
//            url = UPLOAD_BRAND_PREFIX + file.getOriginalFilename();
//            return storeFile(url, file);
//        } catch (Exception e) {
//            throw new IOException(e.getMessage());
//        }
//    }
//
//    public String storeFile(String fileName, MultipartFile file) throws IOException {
//        try{
//            Path filePath = Paths.get(UPLOAD_DIRECTORY + "/" + fileName);
//            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
//            return fileName;
//        } catch (Exception e) {
//            throw new IOException(e.getMessage());
//        }
//    }
//
//    public Resource loadFileAsResource(String fileName) throws IOException{
//        try {
//            Path filePath = Paths.get(fileName);
//            Resource resource = new UrlResource(filePath.toUri());
//            if (resource.exists()) {
//                return resource;
//            } else {
//                throw new FileNotFoundException("File not found " + fileName);
//            }
//        } catch (IOException ex) {
//            throw new IOException("File not found " + fileName, ex);
//        }
//    }
//
//    private String generateNameByCurrentDateTime() {
//        String name = "";
//        Date date = new Date();
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
//        name = sdf.format(date);
//        return name;
//    }
//}
