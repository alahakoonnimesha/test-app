package com.nishiProductions.TestApplication.repository.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FileDto {
    private String multipartFileId;
    private String fileName;
    private String fileType;
    private byte[] data;
    private InputStream inputStreamData;
    private MultipartFile file;
}
