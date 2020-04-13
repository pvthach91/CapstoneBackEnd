package com.pvthach.capstone.controller;

import com.pvthach.capstone.message.response.ApiResponse;
import com.pvthach.capstone.message.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * Created by THACH-PC
 */

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class FileUploadController {
	@Value("${capstone.app.upload}")
	private String UPLOAD_DIRECTORY;

	@Autowired
	FileUploadStorage fileStorage;

	@GetMapping(value = "/api/guest/file/{fileName}")
	public ResponseEntity<InputStreamResource> getFile(@PathVariable String fileName) throws IOException {
		String path = UPLOAD_DIRECTORY + "/" + fileName;
		Resource resource = fileStorage.loadFileAsResource(path);
		return ResponseEntity
				.ok()
				.contentType(MediaType.IMAGE_PNG)
				.body(new InputStreamResource(resource.getInputStream()));
	}

	@PostMapping(value = "api/upload/uploadFarm")
	@PreAuthorize("hasRole('FARMER')")
	public List<String> uploadFarm(@RequestParam("files") MultipartFile[] files) throws IOException {
		if (files == null || files.length ==0) {
			throw new IOException("There's no file to upload");
		}
		return fileStorage.saveFarm(files);
	}

	@PostMapping(value = "api/upload/uploadProduct")
	@PreAuthorize("hasRole('FARMER') or hasRole('ADMIN') or hasRole('PM') or hasRole('BUYER')  or hasRole('DRIVER')")
	public List<String> uploadProduct(@RequestParam("files") MultipartFile[] files) throws IOException {
		if (files == null || files.length ==0) {
			throw new IOException("There's no file to upload");
		}
		return fileStorage.saveProduct(files);
	}

	@PostMapping(value = "api/upload/uploadProfile")
	@PreAuthorize("hasRole('FARMER') or hasRole('ADMIN') or hasRole('PM') or hasRole('BUYER')  or hasRole('DRIVER')")
	public ApiResponse<String> uploadProfile(@RequestParam("file") MultipartFile file) throws IOException {
		if (file == null) {
			throw new IOException("There's no file to upload");
		}
		String result = fileStorage.saveProfilePhoto(file);
		return Response.successResult(result);
	}

	@PostMapping(value = "api/upload/uploadVehicle")
	@PreAuthorize("hasRole('FARMER') or hasRole('DRIVER')")
	public ApiResponse<String> uploadVehicle(@RequestParam("file") MultipartFile file) throws IOException {
		if (file == null) {
			throw new IOException("There's no file to upload");
		}
		String result = fileStorage.saveVehiclePhoto(file);
		return Response.successResult(result);
	}
}