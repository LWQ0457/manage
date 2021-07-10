package com.liuwq.hdfs.manage.controller;

import com.liuwq.hdfs.manage.pojo.FileFactory;
import com.liuwq.hdfs.manage.pojo.Hdfs;
import com.liuwq.hdfs.manage.pojo.HdfsFile;
import com.liuwq.hdfs.manage.pojo.ResultBody;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class HdfsController {
    @Autowired
    Hdfs hdfs;

    @GetMapping("/hello")
    public String hello() {
        return "hello world";
    }

    @GetMapping("/list")
    public ResultBody list() throws Exception {
        ArrayList<HdfsFile> arrayList = new ArrayList<>();
        FileSystem fileSystem = hdfs.getFileSystem();
        FileStatus[] fileStatus = fileSystem.listStatus(new Path(hdfs.getUri()));
        for (int i = 0; i < fileStatus.length; i++) {
            if (fileStatus[i].isFile()) {
                arrayList.add(FileFactory.fileFomater(fileStatus[i]));
            } else if (fileStatus[i].isDirectory()) {
                arrayList.add(FileFactory.fileFomater(fileStatus[i]));
            }
        }
        return ResultBody.seccess(arrayList);
    }
}
