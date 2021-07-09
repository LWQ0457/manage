package com.liuwq.hdfs.manage.pojo;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;

import java.net.URI;

public class Hdfs {
    private final String uri;
    private final Configuration configuration;
    private FileSystem fileSystem;

    public Hdfs(String uri) {
        configuration = new Configuration();
        try {
            fileSystem = FileSystem.get(URI.create(uri), configuration,"root");

        } catch (Exception e) {
            e.printStackTrace();
        }
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
