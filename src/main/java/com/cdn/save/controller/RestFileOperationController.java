package com.cdn.save.controller;

import com.cdn.save.model.Directory.DirectoryRequest;
import com.cdn.save.model.Directory.DirectoryResponse;
import com.cdn.save.model.File.FileRequest;
import com.cdn.save.model.File.FileResponse;
import com.cdn.save.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestFileOperationController {

    @Autowired
    private StorageService storageService;

    @PostMapping("/add")
    public ResponseEntity<FileResponse> addFile(@RequestBody FileRequest fileRequest) {
        return new ResponseEntity<>(storageService.addFile(fileRequest), HttpStatus.OK);
    }
    @PostMapping("/mkdir")
    public ResponseEntity<DirectoryResponse> mkDir(@RequestBody DirectoryRequest directoryRequest) {
        return new ResponseEntity<>(storageService.mkDir(directoryRequest), HttpStatus.OK);
    }
    @PostMapping("/rm")
    public ResponseEntity<DirectoryResponse> rmDir(@RequestBody DirectoryRequest directoryRequest) {
        return new ResponseEntity<>(storageService.rm(directoryRequest), HttpStatus.OK);
    }

//    TODO load files and directories from client directory
//    @GetMapping("/load")
//    public ResponseEntity<FileSystemResponse> load(@RequestParam DirectoryRequest directoryRequest) {
//        return new ResponseEntity<>(storageService.loadDirectory(directoryRequest), HttpStatus.OK);
//    }
}