package com.river.movieReview.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.log4j.Log4j2;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@Log4j2
@Data
@AllArgsConstructor
public class UploadResultDto implements Serializable {

    private String fileName;
    private String uuid;
    private String folderPath;

    public String getImageUrl(){
        try {
            return URLEncoder.encode(folderPath + "/" + uuid + "_" + fileName, "UTF-8");
        } catch (UnsupportedEncodingException e){
            log.error(e);
            return "";
        }
    }

}
