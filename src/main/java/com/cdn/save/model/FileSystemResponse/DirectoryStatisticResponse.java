package com.cdn.save.model.FileSystemResponse;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class DirectoryStatisticResponse {
    private String name;
    private List<FileStatisticResponse> fileList;
    private List<DirectoryStatisticResponse> directoryList;
}
