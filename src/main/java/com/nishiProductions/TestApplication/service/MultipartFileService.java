package com.nishiProductions.TestApplication.service;

import com.nishiProductions.TestApplication.repository.dto.FileDto;
import com.nishiProductions.TestApplication.repository.dto.ResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface MultipartFileService {

    ResponseDto handleFileUpload(List<MultipartFile> multipartFiles) throws IOException;

    ResponseDto handleFileDownload(String fileName) throws IOException;

}
