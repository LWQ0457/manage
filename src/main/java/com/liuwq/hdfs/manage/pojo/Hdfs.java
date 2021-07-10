package com.liuwq.hdfs.manage.pojo;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;

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


}
