package com.river.movieReview.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Log4j2
@RestController
public class UploadController {

    @PostMapping("/uploadAjax")
    public void uploadFile(MultipartFile[] uploads){

        for(MultipartFile file : uploads){

            String originalName = file.getOriginalFilename();
            String fileName = originalName.substring(originalName.lastIndexOf("\\") + 1);

        }

    }

}
