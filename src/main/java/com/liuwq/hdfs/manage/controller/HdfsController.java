package com.liuwq.hdfs.manage.controller;

import com.liuwq.hdfs.manage.pojo.ResultBody;
import com.liuwq.hdfs.manage.service.HdfsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HdfsController {
    @Autowired
    HdfsService hdfsService;

    @GetMapping("/hello")
    public String hello() {
        return "hello world";
    }

    @GetMapping("/list")
    public ResultBody list(@RequestParam("path") String path) throws Exception {
        ResultBody resultBody;
        Object data;
        data = hdfsService.getFileInfoByPath(path);
        resultBody = ResultBody.getResult(data);
        return resultBody;
    }

    @GetMapping("/upload")
    public ResultBody upload() {
        return null;
    }
}
