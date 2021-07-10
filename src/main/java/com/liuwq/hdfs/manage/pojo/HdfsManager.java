package com.liuwq.hdfs.manage.pojo;

import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.Path;

import java.io.IOException;

public interface HdfsManager {
    FSDataOutputStream save(Path path) throws IOException;

    Boolean exist(Path path) throws IOException;

    Boolean delete(Path path) throws IOException;

    FileStatus[] listStatus(Path path) throws Exception;

    Boolean rename(Path oldPath, Path newPath) throws IOException;


    Boolean create(Path path) throws IOException;

    void copyFromLocalFile(Path localPath, Path remotePath) throws IOException;

    FSDataInputStream open(Path path) throws IOException;

    long getFileSize(Path path) throws IOException;
}
