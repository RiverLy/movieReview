package com.river.movieReview.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Log4j2
@RestController
public class UploadController {

    @Value("${com.river.upload.path}")
    private String uploadPath;

    @PostMapping("/uploadAjax")
    public void uploadFile(MultipartFile[] uploads){

        for(MultipartFile file : uploads){

            if(file.getContentType().startsWith("image") == false){
                log.warn("The file should be Image type");
                return;
            }

            String originalName = file.getOriginalFilename();
            String fileName = originalName.substring(originalName.lastIndexOf("\\") + 1);

            log.info("File Name : " + fileName);

            String folderPath = makeFolder();
            String uuid = UUID.randomUUID().toString();

            String saveName = uploadPath + File.separator + folderPath + File.separator + uuid + "_" + fileName;

            Path savePath = Paths.get(saveName);

            try {
                file.transferTo(savePath);
            } catch (IOException e){
                log.error(e);
            }
        }

    }

    private String makeFolder(){

        String date = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        String path = date.replace("/", File.separator);

        File uploadFolder = new File(uploadPath, path);

        if(uploadFolder.exists() == false){
            uploadFolder.mkdirs();
        }

        return path;

    }

}
