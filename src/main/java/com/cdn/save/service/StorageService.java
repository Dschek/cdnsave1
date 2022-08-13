package com.cdn.save.service;

import com.cdn.save.model.Directory.DirectoryRequest;
import com.cdn.save.model.Directory.DirectoryResponse;
import com.cdn.save.model.File.FileRequest;
import com.cdn.save.model.File.FileResponse;
import com.cdn.save.model.FileSystemResponse.DirectoryStatisticResponse;
import com.cdn.save.model.FileSystemResponse.FileSystemResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.function.Function;
import java.util.stream.Stream;

@Service
public class StorageService {

    @Value("${rootLocation}")
    private String ROOT_LOCATION;

    private Path rootPath;

    @Autowired
    public void setRootPath() {
        rootPath = Paths.get(ROOT_LOCATION);
    }


    public FileResponse addFile(FileRequest fileRequest){
        String pathLocation = getFilePath(fileRequest.getLogin(), fileRequest.getPath(), fileRequest.getFile().getContentType());
        Path path = load(pathLocation);
        if(path == null){
            //error
            return null;
        }
        if(fileRequest.getFile() == null){
            //error
            return null;
        }
        if(createFile(fileRequest.getFile(), path)){
            return null;
        }
        //error
        return null;
    }

    public FileResponse rmFile(FileRequest fileRequest){
        String pathLocation = getFilePath(fileRequest.getLogin(), fileRequest.getPath(), fileRequest.getFile().getContentType());
        Path path = load(pathLocation);
        if(path == null){
            //error
            return null;
        }
        if(delete(path)){
            return null;
        }
        //error
        return null;
    }
    public DirectoryResponse mkDir(DirectoryRequest directoryRequest){
        String pathLocation = getDirectoryPath(directoryRequest.getLogin(), directoryRequest.getPath(), directoryRequest.getDirectory());
        Path path = load(pathLocation);
        if(path == null){
            //error
            return null;
        }
        if(createDirectories(path)){
            return null;
        }
        //error
        return null;
    }
    public DirectoryResponse rmDir(DirectoryRequest directoryRequest){
        String pathLocation = getDirectoryPath(directoryRequest.getLogin(), directoryRequest.getPath(), directoryRequest.getDirectory());
        Path path = load(pathLocation);
        if(path == null){
            //error
            return null;
        }
        if(delete(path)){
            return null;
        }
        //error
        return null;
    }

    public String getFilePath(String login, String path, String fileType){
        return  login + "/" + (path==null || path.isEmpty()? "" : path + "/") + System.currentTimeMillis() + "." + fileType;
    }
    public String getDirectoryPath(String login, String path, String directory){
        return  login + "/" + (path==null || path.isEmpty()? "" : path + "/") + directory;
    }

    public boolean createFile(MultipartFile file, Path path) {
        try {
            try (InputStream inputStream = file.getInputStream()) {
                Files.copy(inputStream, path.normalize().toAbsolutePath(),
                        StandardCopyOption.REPLACE_EXISTING);
            }
            return true;
        } catch (IOException e) {
            //
            return false;
        }
    }

//    TODO load files and directories from client directory
//
//    public FileSystemResponse loadDirectory(DirectoryRequest directoryRequest){
//        String pathLocation = getDirectoryPath(directoryRequest.getLogin(), directoryRequest.getPath(), directoryRequest.getDirectory());
//        Path path = load(pathLocation);
//        if(path == null){
//            //error
//            return null;
//        }
//        FileSystemResponse fileSystemResponse = new FileSystemResponse();
//        loadDirectory(path, elem-> DirectoryStatisticResponse.builder()
//                        .directoryList()
//                .directoryList()
//                )
//        if(delete(path)){
//            return null;
//        }
//        //error
//        return null;
//    }

//    public Stream<Path> loadDirectory(Path path, Function function) {
//        try {
//            return Files.list(path).map(function);
//        } catch (IOException e) {
//            //
//            return null;
//        }
//    }

    public Path load(String filePath) {
        return rootPath.resolve(filePath);
    }

    public boolean delete(Path path) {
        try {
            FileSystemUtils.deleteRecursively(path);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean createDirectories(Path path) {
        try {
            Files.createDirectories(path);
            return true;
        } catch (IOException e) {
            return false;
        }
    }
}
