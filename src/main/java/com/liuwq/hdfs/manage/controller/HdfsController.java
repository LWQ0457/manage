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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
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
    public ResultBody list(@RequestParam("uri") String uri) throws Exception {
        FileSystem fileSystem = hdfs.getFileSystem();
        FileStatus[] fileStatus = fileSystem.listStatus(new Path(uri));
        return ResultBody.seccess(FileFactory.fomater(fileStatus));
    }
}
