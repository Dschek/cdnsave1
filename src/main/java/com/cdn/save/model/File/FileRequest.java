package com.cdn.save.model.File;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class FileRequest {
    private String login;
    private Boolean isPrivate;
    private String path;
    private MultipartFile file;
}
