package com.app.serviceImpl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.app.service.FileService;

@Service
public class FileServiceImpl implements FileService {

	@Override
	public String uploadImage(String path, MultipartFile file) throws IOException {

		// file name
		String name = file.getOriginalFilename();

		// generate random file name
		String randomId = UUID.randomUUID().toString();
		String fileName1 = randomId.concat(name.substring(name.lastIndexOf(".")));

		/**
		 * filePath becomes the complete path to the file, including both the directory
		 * and the file name.
		 * 
		 * File.Separator will be used as seperator: \
		 **/
		String filePath = path + File.separator + fileName1;

		// create folder if its not created
		File f = new File(path);
		if (!f.exists()) {
			f.mkdir();
		}

		/**
		 * Files.copy(...): This method copies the content from the input stream (i.e.,
		 * the uploaded file) to the specified file path.
		 * 
		 * It creates a new file at the destination location if it doesn’t already
		 * exist.
		 **/
		Files.copy(file.getInputStream(), Paths.get(filePath));

		return fileName1;
	}

	/**
	 * new FileInputStream(fullPath) creates an input stream from the specified file
	 * path (fullPath).
	 * 
	 * An input stream allows reading data from the file. Returning the Input
	 * Stream: The method returns the input stream, which can be used to read the
	 * content of the resource.
	 **/
	@Override
	public InputStream getResource(String path, String fileName) throws FileNotFoundException {

		String fullPath = path + File.separator + fileName;

		InputStream inputStream = new FileInputStream(fullPath);

		return inputStream;
	}

}
