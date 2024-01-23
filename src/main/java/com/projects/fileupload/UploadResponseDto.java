package com.projects.fileupload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UploadResponseDto {
    private String fileName;
    private String filePath;
    private String fileType;
    private String message;
}