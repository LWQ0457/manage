package com.liuwq.hdfs.manage.service;

import com.liuwq.hdfs.manage.pojo.HdfsFile;
import org.apache.hadoop.fs.Path;

import javax.servlet.ServletOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public interface HdfsService {
    ArrayList<HdfsFile> getFileInfoByPath(String path) throws Exception;

    boolean downloadFile(String path, ServletOutputStream outputStream) throws Exception;

    boolean uploadFile(String path, InputStream inputStream) throws Exception;

    boolean deleteFile(String path) throws Exception;

    boolean creatNewFile(String path) throws Exception;

    long getDirSize(String path) throws Exception;

    boolean renameFile(String oldPath,String newPath) throws Exception;
    boolean exist(String path) throws IOException;
}
