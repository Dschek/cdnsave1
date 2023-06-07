package com.cdn.save.model.Directory;

import lombok.Builder;
import lombok.Data;

@Data
public class DirectoryResponse {
    private String path;
    private int status;
}
