package com.liuwq.hdfs.manage.pojo;


import java.text.SimpleDateFormat;
import java.util.Date;

public class HdfsFile {
    private Boolean isDir;
    private String authority;
    private String owner;
    private String time;
    private Long size;
    private String path;

    public HdfsFile() {
    }

    public HdfsFile(Boolean isDir, String authority, String owner, Date date, Long size, String path) {
        this.isDir = isDir;
        this.authority = authority;
        this.owner = owner;
        this.time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
        this.size = size;
        this.path = path;
    }

    public Boolean getDir() {
        return isDir;
    }

    public void setDir(Boolean isDir) {
        this.isDir = isDir;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
