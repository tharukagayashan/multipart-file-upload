package com.projects.fileupload;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

public interface DocumentService {
    ResponseEntity<UploadResponseDto> uploadFile(MultipartFile file);
}
