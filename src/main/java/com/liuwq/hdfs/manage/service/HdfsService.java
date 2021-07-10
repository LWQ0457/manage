package com.liuwq.hdfs.manage.service;

import com.liuwq.hdfs.manage.pojo.FileFactory;
import com.liuwq.hdfs.manage.pojo.Hdfs;
import com.liuwq.hdfs.manage.pojo.HdfsFile;
import com.liuwq.hdfs.manage.pojo.ResultBody;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

@Service
public class HdfsService {
    @Autowired
    Hdfs hdfs;
    public ArrayList<HdfsFile> getFileInfoByPath(String path)throws Exception{
        if(path.equals("")){
            path="/";
        }
        if (path.charAt(0)!='/'){
            path='/'+path;
        }
        return FileFactory.format(hdfs.getFileByPath(new Path(path)));
    }
    public FSDataInputStream download(String path, HttpServletResponse response)throws Exception{


        return hdfs.open(new Path(path));
    }
}
