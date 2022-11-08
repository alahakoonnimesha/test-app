package com.nishiProductions.TestApplication.controller;

import com.nishiProductions.TestApplication.repository.dto.ResponseDto;
import com.nishiProductions.TestApplication.service.MultipartFileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static java.nio.file.Files.copy;
import static java.nio.file.Paths.get;

@Slf4j
@RestController
@RequestMapping("multipartFile")
@CrossOrigin(origins = "*")
public class MultipartFileController {

    @Autowired
    public MultipartFileService multipartFileService;
    //Save the uploaded file to this folder
    private static String UPLOADED_FOLDER = "C://temp//";

    List<String> files = new ArrayList<String>();
    private final Path rootLocation = get("C:/Code Practice/File upload");

    @RequestMapping(value=("/savefile"),method = RequestMethod.POST)
    public ResponseEntity<String> handleFileUpload(MultipartFile file) {
        String message;
        try {
            try {
                copy(file.getInputStream(), this.rootLocation.resolve(file.getOriginalFilename()+"." + file.getContentType()));
            } catch (Exception e) {
                throw new RuntimeException("FAIL!");
            }
            files.add(file.getOriginalFilename());

            message = "Successfully uploaded!";
            return ResponseEntity.status(HttpStatus.OK).body(message);
        } catch (Exception e) {
            message = "Failed to upload!";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(message);
        }
    }

    //define method to upload files
    @RequestMapping(value=("/upload"),method = RequestMethod.POST)
    public ResponseDto handleFileUpload(@RequestParam("files") List<MultipartFile> multipartFiles) throws IOException {
       return multipartFileService.handleFileUpload(multipartFiles);
    }

    //define method to download files
    @RequestMapping(value=("/download"),method = RequestMethod.GET)
    public ResponseDto handleFileDownload(@RequestParam("fileName") String fileName) throws IOException {
       return multipartFileService.handleFileDownload(fileName);
    }


}
