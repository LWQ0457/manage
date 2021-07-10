package com.liuwq.hdfs.manage.service;

import com.liuwq.hdfs.manage.pojo.FileFactory;
import com.liuwq.hdfs.manage.pojo.Hdfs;
import com.liuwq.hdfs.manage.pojo.ResultBody;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HdfsService {
    @Autowired
    Hdfs hdfs;
    public Object getFileInfoByPath(String path)throws Exception{
        return FileFactory.format(hdfs.getFileByPath(new Path(path)));
    }
}
