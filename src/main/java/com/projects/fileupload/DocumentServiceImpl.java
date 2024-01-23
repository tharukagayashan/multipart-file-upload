package com.projects.fileupload;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.Date;

@Slf4j
@Service
public class DocumentServiceImpl implements DocumentService {
    @Override
    public ResponseEntity<UploadResponseDto> uploadFile(MultipartFile file) {
        try {

            if (file.isEmpty()) {
                log.error("File is empty");
                throw new RuntimeException("File is empty");
            }

            String currentPath = System.getProperty("user.dir");
            log.info("Current path: {}", currentPath);

            String uploadPath = currentPath + "\\uploads\\";

            String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmssSSS").format(new Date());
            String fileName = timeStamp + "_" + file.getOriginalFilename();

            String filePath = uploadPath + fileName;
            String fileType = file.getContentType();

            Path path = Path.of(uploadPath);
            if (!path.toFile().exists()) {
                log.info("Creating directory: {}", uploadPath);
                path.toFile().mkdirs();
            } else {
                log.info("Directory already exists: {}", uploadPath);
            }

            log.info("File name: {}", fileName);
            log.info("File path: {}", filePath);
            log.info("File type: {}", fileType);
            log.info("File size: {}", (file.getSize() / 1024) + " KB");

            File destFile = new File(filePath);
            file.transferTo(destFile);

            UploadResponseDto uploadResponseDto = new UploadResponseDto();
            uploadResponseDto.setFileName(fileName);
            uploadResponseDto.setFilePath(filePath);
            uploadResponseDto.setFileType(fileType);
            uploadResponseDto.setMessage("File uploaded successfully");
            return ResponseEntity.ok(uploadResponseDto);

        } catch (Exception e) {
            log.error("Error while uploading file: {}", e.getMessage());
            e.fillInStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }
}
