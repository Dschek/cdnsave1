package com.cdn.save.model.Directory;

import lombok.Data;

@Data
public class DirectoryRequest {
    private String login;
    private Boolean isPrivate;
    private String directory;
}
