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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
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
        return ResultBody.success(data);
    }

    @PostMapping("/upload")
    public ResultBody upload(@RequestParam("path") String path,
                             @RequestPart("file") MultipartFile file) throws Exception{
        hdfsService.uploadFile(path+file.getOriginalFilename(),file.getInputStream());
        return ResultBody.success(null);
    }
    @GetMapping("/download")
    public ResultBody download(@RequestParam("path") String path,
                                 HttpServletResponse response)throws Exception{
        if(path.charAt(0)!='/'){
            path='/'+path;
        }
        response.reset();
        response.setHeader("Content-Disposition", "attachment; filename=\"" + path.substring(1) + "\"");
        response.addHeader("Content-Length", "" + (hdfsService.getFileInfoByPath(path)).get(0).getSize());
        response.setContentType("application/octet-stream;charset=UTF-8");
        ServletOutputStream outputStream = response.getOutputStream();
        hdfsService.downloadFile(path,outputStream);
        return ResultBody.success(null);
    }
    @GetMapping("/delete")
    public ResultBody deleteFile(@RequestParam("path") String path) throws Exception {
        hdfsService.deleteFile(path);
        return ResultBody.success(null);
    }
    @GetMapping("add")
    public ResultBody addFile(@RequestParam("path") String path) throws Exception {
        hdfsService.creatNewFile(path);
        return ResultBody.success(null);
    }
    @GetMapping("getSize")
    public ResultBody getDirSize(@RequestParam("path") String path) throws IOException {
        return ResultBody.success(hdfsService.getDirSize(path));
    }
}
