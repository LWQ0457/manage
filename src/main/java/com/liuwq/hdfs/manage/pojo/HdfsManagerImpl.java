package com.liuwq.hdfs.manage.pojo;

import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.Path;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.IOException;

@Repository
public class HdfsManagerImpl implements HdfsManager {
    @Autowired
    Hdfs hdfs;

    @Override
    public FileStatus[] listStatus(Path path) throws Exception {
        return hdfs.getFileSystem().listStatus(path);
    }

    @Override
    public FSDataOutputStream save(Path path) throws IOException {
        return hdfs.getFileSystem().create(path);
    }

    @Override
    public Boolean exist(Path path) throws IOException {
        return hdfs.getFileSystem().exists(path);
    }

    @Override
    public Boolean delete(Path path) throws IOException {
        return hdfs.getFileSystem().delete(path, true);
    }

    @Override
    public Boolean rename(Path oldPath, Path newPath) throws IOException {
        return hdfs.getFileSystem().rename(oldPath, newPath);
    }

    @Override
    public Boolean create(Path path) throws IOException {
        return hdfs.getFileSystem().createNewFile(path);
    }

    @Override
    public void copyFromLocalFile(Path localPath, Path remotePath) throws IOException {
        hdfs.getFileSystem().copyFromLocalFile(localPath, remotePath);
    }

    @Override
    public FSDataInputStream open(Path path) throws IOException {
        return hdfs.getFileSystem().open(path);
    }

    @Override
    public long getFileSize(Path path) throws IOException {
        return hdfs.getFileSystem().getUsed(path);
    }
}
