package com.liuwq.hdfs.manage.pojo;

import org.apache.hadoop.fs.FileStatus;

import java.util.Date;

public class FileFactory {
    public static HdfsFile fileFomater(FileStatus fileStatus){
        return new HdfsFile(fileStatus.isDirectory(),fileStatus.getPermission().toString(),fileStatus.getOwner(),
                new Date(fileStatus.getModificationTime()),fileStatus.getLen(),fileStatus.getPath().toString());
    }
}
