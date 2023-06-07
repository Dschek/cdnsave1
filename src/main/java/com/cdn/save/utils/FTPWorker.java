package com.cdn.save.utils;

import org.apache.commons.net.ftp.FTPClient;
import java.io.IOException;
import java.io.InputStream;
public class FTPWorker {
    private FTPClient ftp;

    public FTPWorker(String host, Integer port, String login, String password) throws IOException {
        ftp = new FTPClient();
        ftp.connect(host, port);
        ftp.login(login, password);
    }

    public void close() throws IOException {
        ftp.disconnect();
    }

    public int mkDir(String path) throws IOException {
        int result = 0;
        if (path != null || path.isEmpty()) {
            String[] pathArr = path.split("/");
            String mkPath = "";
            for (String dirName : pathArr) {
                mkPath += dirName + "/";
                result = ftp.mkd(mkPath);
            }
        }
        return result;
    }

    public int rmDir(String path) throws IOException {
        int result = 0;
        if (path != null || path.isEmpty()) {
            ftp.rmd(path);
            int i = path.lastIndexOf("/");
            if (i != -1 && path.endsWith("/")) {
                i = path.substring(0, i).lastIndexOf("/");
            }
            while (i != -1) {
                String newPath = path.substring(0, i);
                result = ftp.rmd(newPath);
                i = newPath.lastIndexOf("/");
            }
        }
        return result;
    }

    public boolean addFile(String path, InputStream file) throws IOException {
//        mkDir(path);
        return ftp.appendFile(path, file);
    }

    public int rmFile(String path) throws IOException {
        return ftp.rmd(path);
    }
}
