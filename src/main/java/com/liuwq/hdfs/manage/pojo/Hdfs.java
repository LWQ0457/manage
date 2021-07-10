package com.liuwq.hdfs.manage.pojo;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.*;

import java.io.IOException;
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
    public FSDataOutputStream save(Path path)throws Exception{
        return fileSystem.create(path);
    }
    public Boolean exist(Path path) throws Exception{
        return fileSystem.exists(path);
    }
    public Boolean delete(Path path) throws Exception{
        return fileSystem.delete(path,true);
    }

    public Boolean rename(Path oldPath,Path newPath)throws Exception{
        return fileSystem.rename(oldPath,newPath);
    }
    public Boolean move(){
        return true;
    }
    public Boolean create(Path path)throws Exception{
        return fileSystem.createNewFile(path);
    }
    public void copyFromLocalFile(Path localPath,Path remotePath)throws Exception{
        fileSystem.copyFromLocalFile(localPath, remotePath);
    }
    public FSDataInputStream open(Path path)throws Exception{
        return fileSystem.open(path);
    }
    public long getFileSize(Path path) throws IOException {
        return fileSystem.getUsed(path);
    }
}
