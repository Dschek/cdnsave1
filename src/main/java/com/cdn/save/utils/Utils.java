package com.cdn.save.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringJoiner;
import java.util.stream.Collectors;

public class Utils {
    public static String getFilePath(String login, String path, String fileType){
        return  login + "/" + (path==null || path.isEmpty()? "" : path + "/") + System.currentTimeMillis() + "." + fileType;
    }
    public static String getDirectoryPath(String login, Boolean isPrivate, String directory){
        return login + "/" + (isPrivate? "private" : "public") + (directory.startsWith("/")?"":"/")+directory;
    }

    public static String getPathLocation(String host, String pathFile){
        return host + (host.endsWith("/")?"":"/") + pathFile;
    }

}
