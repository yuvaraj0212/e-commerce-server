package com.example.demo.service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.exception.ExceptionController;
import com.example.demo.pojo.FileStorageProperties;

@Service
public class FileStorageService extends ExceptionController{
	 private final Path fileStorageLocation;
	    int length = 15;
	    boolean useLetters = true;
	    boolean useNumbers = true;
	    @Autowired
	    public FileStorageService(FileStorageProperties fileStorageProperties) throws Exception {
	        this.fileStorageLocation = Paths.get(fileStorageProperties.getUploadDir())
	                .toAbsolutePath().normalize();

	        try {
	            Files.createDirectories(this.fileStorageLocation);
	        } catch (Exception ex) {
	            throw new Exception("Could not create the directory where the uploaded files will be stored.", ex);
	        }
	    }

	    public String storeFile(MultipartFile file) throws Exception {
	        // Normalize file name
	        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
//	        String fileName = RandomStringUtils.random(length, useLetters, useNumbers)+".jpg";
	        try {
	            // Check if the file's name contains invalid characters
	            if(fileName.contains("..")) {
	                throw new Exception("Sorry! Filename contains invalid path sequence " + fileName);
	            }

	            // Copy file to the target location (Replacing existing file with the same name)
	            Path targetLocation = this.fileStorageLocation.resolve(fileName);
	            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

	            return fileName;
	        } catch (IOException ex) {
	            throw new Exception("Could not store file " + fileName + ". Please try again!", ex);
	        }
	    }

	    public Resource loadFileAsResource(String fileName) throws Exception {
	        try {
	            Path filePath = this.fileStorageLocation.resolve(fileName).normalize();
	            Resource resource = new UrlResource(filePath.toUri());
	            if(resource.exists()) {
	                return resource;
	            } else {
	                throw new Exception("File not found " + fileName);
	            }
	        } catch (MalformedURLException ex) {
	            throw new Exception("File not found " + fileName, ex);
	        }
	    }
}
