package com.liuwq.hdfs.manage.pojo;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.tomcat.jni.File;

import java.net.URI;

public class Hdfs {
    private final String uri;
    private final Configuration configuration;
    private FileSystem fileSystem;

    public Hdfs(String uri) throws Exception{
        configuration = new Configuration();
        fileSystem = FileSystem.get(URI.create(uri), configuration,"root");
        this.uri = uri;
    }

    public String getUri() {
        return uri;
    }

    public Configuration getConfiguration() {
        return configuration;
    }

    public FileSystem getFileSystem() {
        return fileSystem;
    }
    public FileStatus[] getFileByPath(Path path) throws Exception{
        return fileSystem.listStatus(path);
    }
    public void save(){
    }
    public Boolean exist(Path path) throws Exception{
        return fileSystem.exists(path);
    }
}
