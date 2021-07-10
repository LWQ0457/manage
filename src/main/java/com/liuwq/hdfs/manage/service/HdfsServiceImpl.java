package com.liuwq.hdfs.manage.service;

import com.liuwq.hdfs.manage.pojo.FileFormat;
import com.liuwq.hdfs.manage.pojo.HdfsFile;
import com.liuwq.hdfs.manage.pojo.HdfsManager;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.ServletOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

@Service
public class HdfsServiceImpl implements HdfsService {
    @Autowired
    HdfsManager hdfsManager;
    @Autowired
    FileFormat fileFormat;

    @Override
    public ArrayList<HdfsFile> getFileInfoByPath(String path) throws Exception {
        return fileFormat.format(hdfsManager.listStatus(pathFormat(path)));
    }

    @Override
    public boolean downloadFile(String path, ServletOutputStream outputStream) throws Exception {
        IOUtils.copyBytes(hdfsManager.open(pathFormat(path)), outputStream, 4096, true);
        return true;
    }

    @Override
    public boolean uploadFile(String path, InputStream inputStream) throws Exception {
        IOUtils.copyBytes(inputStream, hdfsManager.save(pathFormat(path)), 4096, true);
        return true;
    }

    @Override
    public boolean deleteFile(String path) throws Exception {
        return hdfsManager.delete(pathFormat(path));
    }

    @Override
    public boolean creatNewFile(String path) throws Exception {
        return hdfsManager.create(pathFormat(path));
    }

    @Override
    public long getDirSize(String path) throws IOException {
        return hdfsManager.getFileSize(pathFormat(path));
    }

    public Path pathFormat(String path) {
        if (!path.startsWith("hdfs")) {
            if (path.charAt(0) != '/') {
                path = '/' + path;
            }
        }
        return new Path(path);
    }
}
