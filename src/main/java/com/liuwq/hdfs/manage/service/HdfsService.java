package com.liuwq.hdfs.manage.service;

import com.liuwq.hdfs.manage.pojo.FileFactory;
import com.liuwq.hdfs.manage.pojo.Hdfs;
import com.liuwq.hdfs.manage.pojo.HdfsFile;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.ServletOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

@Service
public class HdfsService {
    @Autowired
    Hdfs hdfs;

    public ArrayList<HdfsFile> getFileInfoByPath(String path) throws Exception {
        return FileFactory.format(hdfs.getFileByPath(pathFormat(path)));
    }

    public boolean downloadFile(String path, ServletOutputStream outputStream) throws Exception {
        IOUtils.copyBytes(hdfs.open(pathFormat(path)), outputStream, 4096, true);
        return true;
    }

    public boolean uploadFile(String path, InputStream inputStream) throws Exception {
        IOUtils.copyBytes(inputStream, hdfs.save(pathFormat(path)), 4096, true);
        return true;
    }

    public boolean deleteFile(String path) throws Exception {
        return hdfs.delete(pathFormat(path));
    }

    public boolean creatNewFile(String path) throws Exception {
        return hdfs.create(pathFormat(path));
    }
    public long getDirSize(String path) throws IOException {
        return hdfs.getFileSize(pathFormat(path));
    }
    public Path pathFormat(String path) {
        if(!path.startsWith("hdfs")){
            if (path.charAt(0) != '/') {
                path = '/' + path;
            }
        }
        return new Path(path);
    }
}
