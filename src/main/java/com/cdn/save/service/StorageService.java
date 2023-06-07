package com.cdn.save.service;

import com.cdn.save.utils.FTPWorker;
import com.cdn.save.model.Directory.DirectoryRequest;
import com.cdn.save.model.Directory.DirectoryResponse;
import com.cdn.save.model.File.FileRequest;
import com.cdn.save.model.File.FileResponse;
import com.cdn.save.utils.Utils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.io.IOException;

@Service
@Slf4j
public class StorageService {
    @Value("${sftp.host}")
    private String host;
    @Value("${sftp.port}")
    private Integer port;
    @Value("${sftp.login}")
    private String login;
    @Value("${sftp.password}")
    private String password;
    @Value("${app.host.img}")
    private String imgHost;

    private FTPWorker connectFTP() throws IOException {
        return new FTPWorker(host, port, login, password);
    }

    public FileResponse addFile(FileRequest fileRequest){
        String path = Utils.getFilePath(fileRequest.getLogin(), fileRequest.getPath(), fileRequest.getFile().getContentType());
        FileResponse fileResponse = new FileResponse();
        fileResponse.setPath(Utils.getPathLocation(imgHost, path));
        try {
            FTPWorker ftp = connectFTP();
            fileResponse.setStatus(ftp.addFile(path, fileRequest.getFile().getInputStream())?200:500);
            ftp.close();
        } catch (Exception e) {
            log.error("Error create dir", e);
            fileResponse.setStatus(0);
        }
        return fileResponse;
    }
    public DirectoryResponse mkDir(DirectoryRequest directoryRequest){
        String path = Utils.getDirectoryPath(directoryRequest.getLogin(), directoryRequest.getIsPrivate(), directoryRequest.getDirectory());
        DirectoryResponse directoryResponse = new DirectoryResponse();
        directoryResponse.setPath(Utils.getPathLocation(imgHost, path));
        try {
            FTPWorker ftp = connectFTP();
            directoryResponse.setStatus(ftp.mkDir(path));
            ftp.close();
        } catch (Exception e) {
            log.error("Error create dir", e);
            directoryResponse.setStatus(0);
        }
        return directoryResponse;
    }
    public DirectoryResponse rm(DirectoryRequest directoryRequest){
        String path = Utils.getDirectoryPath(directoryRequest.getLogin(), directoryRequest.getIsPrivate(), directoryRequest.getDirectory());
        DirectoryResponse directoryResponse = new DirectoryResponse();
        directoryResponse.setPath(Utils.getPathLocation(imgHost, path));
        try {
            FTPWorker ftp = connectFTP();
            directoryResponse.setStatus(ftp.rmDir(path));
            ftp.close();
        } catch (Exception e) {
            log.error("Error rm dir", e);
            directoryResponse.setStatus(0);
        }
        return directoryResponse;
    }
}
