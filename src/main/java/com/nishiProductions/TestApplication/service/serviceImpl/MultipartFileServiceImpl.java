package com.nishiProductions.TestApplication.service.serviceImpl;

import com.nishiProductions.TestApplication.customExceptions.FileStorageException;
import com.nishiProductions.TestApplication.repository.dto.FileDto;
import com.nishiProductions.TestApplication.repository.dto.ResponseDto;
import com.nishiProductions.TestApplication.repository.dto.UserDto;
import com.nishiProductions.TestApplication.service.MultipartFileService;
import com.nishiProductions.TestApplication.util.ServiceUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static java.nio.file.Files.copy;
import static java.nio.file.Paths.get;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

@Slf4j
@Service
public class MultipartFileServiceImpl implements MultipartFileService {

    @Value("${upload.path}")
    public String uploadPath;

    @Autowired
    ServiceUtil serviceUtil;

    //define location
    public static final String DIRECTORY = "C:/Code Practice/File upload";

//    public ResponseDto storeFile(MultipartFileDto multipartFileDto)
//    {
//        String fileName = StringUtils.cleanPath(multipartFileDto.getFileName());

//        byte[] bytes = multipartFileDto.getData();
//        Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
//        Files.write(path, bytes);
//        MultipartFileDto save = dbFileStorageService.storeFile(file);
//
//        try {
//            // Check if the file's name contains invalid characters
//            if(fileName.contains("..")) {
//                log.info("Sorry! Filename contains invalid path sequence " + fileName);
//            }
//
//            MultipartFile multipartFile = new MultipartFile(fileName, file.getContentType(), file.getBytes());
//
//            return dbFileRepository.save(dbFile);
//        } catch (IOException ex) {
//            throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
//        }
//    }

//    public void storeFile(FileDto fileDto) throws FileUploadException {
//
//        try {
//            Path root = Paths.get(uploadPath);
//            Path resolve = root.resolve(fileDto.getFileName());
//            if (resolve.toFile()
//                    .exists()) {
//                throw new FileStorageException("File already exists: " + fileDto.getFileName());
//            }
//            InputStream inputStream =  new BufferedInputStream(fileDto.getFile().getInputStream());
//
//            Files.copy(inputStream, resolve);
//
////            // Get the file and save it somewhere
////            byte[] bytes = file.getBytes();
////            Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
////            Files.write(path, bytes);
//        } catch (Exception e) {
//            throw new FileUploadException("Could not store the file. Error: " + e.getMessage());
//        }
//    }

    public ResponseDto handleFileUpload(List<MultipartFile> multipartFiles) throws IOException {
        List<String> fileNames = new ArrayList<>();
        log.info("MultipartFileServiceImpl.handleFileUpload(Long clientId) invoked.");
        ResponseDto responseDto = null;
        try {
            for (MultipartFile file: multipartFiles)
            {
                String fileName = StringUtils.cleanPath(file.getOriginalFilename());
                Path fileStoragePath = get(DIRECTORY,fileName).toAbsolutePath().normalize();
                copy(file.getInputStream(),fileStoragePath,REPLACE_EXISTING);
                fileNames.add(fileName);

            }
            if (multipartFiles != null && !multipartFiles.isEmpty()) {
                responseDto = serviceUtil.getServiceResponse(fileNames);
            } else {
                responseDto = serviceUtil.getErrorServiceResponse("error");
            }
        } catch (Exception e) {
            log.error("sdsdsdsd", e);
            responseDto = serviceUtil.getExceptionServiceResponseByProperties("exception");
        }

        return responseDto;
    }

    public ResponseDto handleFileDownload(String fileName) throws IOException {
        log.info("MultipartFileServiceImpl.handleFileUpload(Long clientId) invoked.");
        ResponseDto responseDto = null;
        Path filePath = get(DIRECTORY).toAbsolutePath().normalize().resolve(fileName);
        try {
            if(!Files.exists(filePath))
            {
                responseDto = serviceUtil.getErrorServiceResponse(fileName + "WAS NOT FOUND ON THE SERVER");

            }
            Resource resource = new UrlResource(filePath.toUri());
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.add("file-name",fileName);
            httpHeaders.add(HttpHeaders.CONTENT_DISPOSITION,"attachment; file-name=" + resource.getFilename());
            responseDto = serviceUtil.getServiceResponse(MediaType.parseMediaType(Files.probeContentType(filePath)));

//            return ResponseEntity.ok().contentType(MediaType.parseMediaType(Files.probeContentType(filePath))).headers(httpHeaders).body(resource);
        } catch (Exception e) {
            log.error("sdsdsdsd", e);
            responseDto = serviceUtil.getExceptionServiceResponseByProperties("exception");
        }

        return responseDto;
    }
}
