package com.springboot.at.service;

import java.io.InputStream;
import org.springframework.web.multipart.MultipartFile;

public interface FileStorageService {

  String downloadFileFromUrl(String fileUrl, String destinationPath, String destinationFile);

  InputStream readFile(String folder, String fileName);

  String saveFile(String uploadTo, MultipartFile file, String destinationFile);

  String saveFile(String uploadTo, MultipartFile file);
}
