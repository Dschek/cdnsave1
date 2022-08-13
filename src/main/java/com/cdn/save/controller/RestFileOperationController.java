package com.cdn.save.controller;

import com.cdn.save.model.Directory.DirectoryRequest;
import com.cdn.save.model.Directory.DirectoryResponse;
import com.cdn.save.model.File.FileRequest;
import com.cdn.save.model.File.FileResponse;
import com.cdn.save.model.FileSystemResponse.FileSystemResponse;
import com.cdn.save.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestFileOperationController {

    @Autowired
    private StorageService storageService;

    @PostMapping("/add")
    public ResponseEntity<FileResponse> addFile(@RequestParam FileRequest fileRequest) {
        return new ResponseEntity<>(storageService.addFile(fileRequest), HttpStatus.OK);
    }
    @PostMapping("/rm")
    public ResponseEntity<FileResponse> rmFile(@RequestParam FileRequest fileRequest) {
        return new ResponseEntity<>(storageService.rmFile(fileRequest), HttpStatus.OK);
    }
    @PostMapping("/mkdir")
    public ResponseEntity<DirectoryResponse> mkDir(@RequestParam DirectoryRequest directoryRequest) {
        return new ResponseEntity<>(storageService.mkDir(directoryRequest), HttpStatus.OK);
    }
    @PostMapping("/rmdir")
    public ResponseEntity<DirectoryResponse> rmDir(@RequestParam DirectoryRequest directoryRequest) {
        return new ResponseEntity<>(storageService.rmDir(directoryRequest), HttpStatus.OK);
    }

//    TODO load files and directories from client directory
//    @GetMapping("/load")
//    public ResponseEntity<FileSystemResponse> load(@RequestParam DirectoryRequest directoryRequest) {
//        return new ResponseEntity<>(storageService.loadDirectory(directoryRequest), HttpStatus.OK);
//    }
}