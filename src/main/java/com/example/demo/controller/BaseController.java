package com.example.demo.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.MultipartFile;

@Controller
@PropertySource(value = { "classpath:application.properties" })
public class BaseController {
	@Value("${image.filepath}")
	String filepath;
	
	public void writeByte(byte bytes[], String filename) throws Exception {
		File file = new File(filepath + filename);
		ImageIO.read(file);
	}
	public File multipartToFile(MultipartFile multipart) throws IllegalStateException, IOException {
		Path filepath1 = Paths.get(filepath, multipart.getOriginalFilename());
		multipart.transferTo(filepath1);
		String fileName = multipart.getOriginalFilename();
		String[] dStirng = fileName.split(Pattern.quote("."));
		File convFile1 = File.createTempFile(filepath + dStirng[0], "." + dStirng[1]);
		return convFile1;
	}
	public static byte[] readContentIntoByteArray(File file) {
		FileInputStream fileInputStream = null;
		byte[] bFile = new byte[(int) file.length()];
		try {
			fileInputStream = new FileInputStream(file);
			fileInputStream.read(bFile);
			fileInputStream.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return bFile;
	}
}
