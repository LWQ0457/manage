package com.liuwq.hdfs.manage.controller;

import com.liuwq.hdfs.manage.pojo.Hdfs;
import com.liuwq.hdfs.manage.pojo.HdfsFile;
import com.liuwq.hdfs.manage.pojo.ResultBody;
import com.liuwq.hdfs.manage.service.HdfsService;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.util.ArrayList;

@RestController
public class HdfsController {
    private final static Logger logger= LoggerFactory.getLogger(HdfsController.class);
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
    @GetMapping("/download")
    public void download(@RequestParam("path") String path,
                                 HttpServletResponse response)throws Exception{
        String fileName=path;
        if(path.charAt(0)=='/'){
            fileName=path.substring(1);
        }
        else{
            path='/'+path;
        }
        response.reset();
        response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
        response.addHeader("Content-Length", "" + (hdfsService.getFileInfoByPath(path)).get(0).getSize());
        response.setContentType("application/octet-stream;charset=UTF-8");
        ServletOutputStream outputStream = response.getOutputStream();
        IOUtils.copyBytes(hdfsService.download(path,response),outputStream,4096,true);

    }
}
