package com.springboot.at.service.impl;

import com.springboot.at.service.FileStorageService;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class FileStorageServiceImpl implements FileStorageService {

  private static final Logger log = LogManager.getLogger(FileStorageServiceImpl.class);

  @Value("${file.storage.dir}")
  private String fileStorageLocation;

  @Override
  public String downloadFileFromUrl(String fileUrl, String destinationPath,
      String destinationFile) {
    String newFileName = "";
    try {
      URL url = new URL(fileUrl);
      Path path = Path.of(String.format("%s/%s", fileStorageLocation, destinationPath));
      Path filePath = path.resolve(destinationFile);

      if (!Files.exists(path)) {
        Files.createDirectories(path);
      }

      try (
          InputStream is = url.openStream();
          OutputStream os = new FileOutputStream(filePath.toString())
      ) {
        byte[] b = new byte[2048];
        int length;

        while ((length = is.read(b)) != -1) {
          os.write(b, 0, length);
        }

        newFileName = String.format("/files/%s/%s", destinationPath, destinationFile);
      }
    } catch (Exception e) {
      log.warn("downloadFileFromUrl Failed", e);
    }

    return newFileName;
  }

  @Override
  public InputStream readFile(String folder, String fileName) {
    try {
      Path imagePath = Path.of(folder, fileName);
      if (Files.exists(imagePath)) {
        return Files.newInputStream(imagePath);
      }
    } catch (Exception e) {
      log.warn("readFile Failed", e);
    }

    return null;
  }

  @Override
  public String saveFile(String uploadTo, MultipartFile file, String destinationFile) {
    String newFileName = "";
    try {
      Path uploadPath = Path.of(String.format("%s/%s", fileStorageLocation, uploadTo));
      Path filePath = uploadPath.resolve(destinationFile);

      if (!Files.exists(uploadPath)) {
        Files.createDirectories(uploadPath);
      }

      Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
      newFileName = String.format("/files/%s/%s", uploadTo, destinationFile);
    } catch (Exception e) {
      log.warn("saveFile Failed", e);
    }
    return newFileName;
  }

  @Override
  public String saveFile(String uploadTo, MultipartFile file) {
    return saveFile(uploadTo, file, file.getOriginalFilename());
  }
}
