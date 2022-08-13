package com.cdn.save.model.FileSystemResponse;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class FileSystemResponse {
    private List<DirectoryStatisticResponse> directoryList;
}
