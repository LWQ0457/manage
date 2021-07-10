package com.liuwq.hdfs.manage.pojo;

import org.apache.hadoop.fs.FileStatus;

import java.util.ArrayList;
import java.util.Date;

public class FileFactory {
    public static HdfsFile fileFormat(FileStatus fileStatus){
        return new HdfsFile(fileStatus.isDirectory(),fileStatus.getPermission().toString(),fileStatus.getOwner(),
                new Date(fileStatus.getModificationTime()),fileStatus.getLen(),fileStatus.getPath().toString());
    }
    public static ArrayList<HdfsFile> directoryFormat(FileStatus[] directory){
        ArrayList<HdfsFile> hdfsFiles=new ArrayList<>();
        for (FileStatus fileStatus:directory) {
            hdfsFiles.add(fileFormat(fileStatus));
        }
        return hdfsFiles;
    }
    public static ArrayList<HdfsFile> format(FileStatus[] fileStatuses){
        if (fileStatuses.length==0){
            return null;
        }
        else{
            return directoryFormat(fileStatuses);
        }
    }

}
