package com.liuwq.hdfs.manage.controller;

import com.liuwq.hdfs.manage.pojo.Hdfs;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class HdfsController {
    @Autowired
    Hdfs hdfs;

    @GetMapping("/hello")
    public String hello() {
        return "hello world";
    }

    @GetMapping("/list")
    public List<String> list() {
        System.out.println("adsfaiwefoanosn");
        ArrayList<String> arrayList = new ArrayList<>();
        FileSystem fileSystem = hdfs.getFileSystem();
        try {
            FileStatus[] fileStatus = fileSystem.listStatus(new Path(hdfs.getUri()));
            for (int i = 0; i < fileStatus.length; i++) {

                if (fileStatus[i].isFile()) {
                    arrayList.add(fileStatus[i].getPath().toString());
                } else if (fileStatus[i].isDirectory()) {
                    arrayList.add(fileStatus[i].getPath().toString());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return arrayList;
    }
}
