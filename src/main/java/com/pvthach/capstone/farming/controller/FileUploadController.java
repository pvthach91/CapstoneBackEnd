package com.pvthach.capstone.farming.controller;

import com.pvthach.capstone.message.response.ApiResponse;
import com.pvthach.capstone.message.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
	@Value("${foodproducer.app.upload}")
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

	@PostMapping(value = "api/upload/uploadDishPhoto")
//	@PreAuthorize("hasRole('SALE')")
	public List<String> uploadDishes(@RequestParam("files") MultipartFile[] files) throws IOException {
		if (files == null || files.length ==0) {
			throw new IOException("There's no file to upload");
		}
		return fileStorage.saveDishPhotos(files);
	}

	@PostMapping(value = "api/upload/uploadEmployeePhoto")
//	@PreAuthorize("hasRole('HR')")
	public ApiResponse<String> uploadEmployeePhoto(@RequestParam("file") MultipartFile file) throws IOException {
		if (file == null) {
			throw new IOException("There's no file to upload");
		}
		String result = fileStorage.saveEmployeePhoto(file);
		return Response.successResult(result);
	}
}