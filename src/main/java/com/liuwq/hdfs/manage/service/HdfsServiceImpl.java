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
    private Path filePath;
    private Path filePath2;
    @Override
    public boolean renameFile(String oldPath,String newPath) throws Exception {
        filePath=pathFormat(oldPath);
        filePath2=pathFormat(newPath);
        if(!exist(filePath)||exist(filePath2)){
            return false;
        }
        return hdfsManager.rename(pathFormat(oldPath),pathFormat(newPath));
    }

    @Override
    public boolean exist(Path path) throws IOException {
        return hdfsManager.exist(path);
    }

    @Override
    public ArrayList<HdfsFile> getFileInfoByPath(String path) throws Exception {
        return fileFormat.format(hdfsManager.listStatus(pathFormat(path)));
    }

    @Override
    public boolean downloadFile(String path, ServletOutputStream outputStream) throws Exception {
        filePath=pathFormat(path);
        if (!exist(filePath)){
            return false;
        }
        IOUtils.copyBytes(hdfsManager.open(filePath), outputStream, 4096, true);
        return true;
    }

    @Override
    public boolean uploadFile(String path, InputStream inputStream) throws Exception {
        filePath=pathFormat(path);
        if (exist(filePath)){
            return false;
        }
        IOUtils.copyBytes(inputStream, hdfsManager.save(pathFormat(path)), 4096, true);
        return true;
    }

    @Override
    public boolean deleteFile(String path) throws Exception {
        filePath=pathFormat(path);
        if (!exist(filePath)){
            return false;
        }
        return hdfsManager.delete(pathFormat(path));
    }

    @Override
    public boolean creatNewFile(String path) throws Exception {
        filePath=pathFormat(path);
        if (exist(filePath)){
            return false;
        }
        return hdfsManager.create(pathFormat(path));
    }

    @Override
    public long getDirSize(String path) throws IOException {
        filePath=pathFormat(path);
        if (!exist(filePath)){
            return -1;
        }
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
