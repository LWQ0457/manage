package com.liuwq.hdfs.manage.controller;

import com.liuwq.hdfs.manage.pojo.ResultBody;
import com.liuwq.hdfs.manage.service.HdfsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;


@RestController
public class HdfsController {
    private final static Logger logger = LoggerFactory.getLogger(HdfsController.class);
    @Autowired
    HdfsService hdfsService;

    @GetMapping("/hello")
    public String hello() {
        return "hello world";
    }

    @GetMapping("/list")
    public ResultBody list(@RequestParam("path") String path) throws Exception {
        ResultBody resultBody;
        Object data=null;
        data = hdfsService.getFileInfoByPath(path);
        return ResultBody.success(data);
    }

    @PostMapping("/upload")
    public ResultBody upload(@RequestParam("path") String path,
                             @RequestPart("file") MultipartFile file) throws Exception {
        if (hdfsService.uploadFile(path + file.getOriginalFilename(), file.getInputStream())) {
            return ResultBody.success(path.substring(0,path.lastIndexOf("/")+1));
        }
        return ResultBody.error("-1", "目标路径下存在同名文件");
    }

    @GetMapping("/download")
    public void download(@RequestParam("path") String path,
                               HttpServletResponse response) throws Exception {
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Content-Disposition", "attachment; filename=\"" + path + "\"");
        response.addHeader("Content-Length", "" + (hdfsService.getFileInfoByPath(path)).get(0).getSize());
        response.setContentType("application/vnd.openxmlformats-officedocument.wordprocessingml.document;charset=UTF-8");
        response.addHeader("Pragma", "no-cache");
        response.addHeader("Expires", "0");
        response.addHeader("Last-Modified", new Date().toString());
        response.addHeader("ETag", String.valueOf(System.currentTimeMillis()));
        ServletOutputStream outputStream = response.getOutputStream();
        hdfsService.downloadFile(path, outputStream);
//        if (hdfsService.downloadFile(path, outputStream)) {
//            return ResultBody.success(null);
//        }
//        return ResultBody.error_noFind();
    }

    @GetMapping("/delete")
    public ResultBody deleteFile(@RequestParam("path") String path) throws Exception {
        if (hdfsService.deleteFile(path)) {
            return ResultBody.success(hdfsService.getFileInfoByPath(path.substring(0,path.lastIndexOf("/")+1)));
        } else {
            return ResultBody.error_noFind();
        }
    }

    @GetMapping("add")
    public ResultBody addFile(@RequestParam("path") String path) throws Exception {
        if (hdfsService.creatNewFile(path))
            return ResultBody.success(hdfsService.getFileInfoByPath(path.substring(0,path.lastIndexOf("/")+1)));
        else {
            return ResultBody.error_noFind();
        }
    }

    @GetMapping("getSize")
    public ResultBody getDirSize(@RequestParam("path") String path) throws Exception {
        long size;
        if ((size = hdfsService.getDirSize(path)) > -1)
            return ResultBody.success(size);
        else {
            return ResultBody.error_noFind();
        }
    }

    @GetMapping(value = {"rename", "move"})
    public ResultBody renameFile(@RequestParam("srcPath") String srcPath,
                                 @RequestParam("desPath") String desPath) throws Exception {
        if (hdfsService.renameFile(srcPath, desPath)) {
            return ResultBody.success(null);

        } else {
            return ResultBody.error("-1", "目标路径下存在同名文件");
        }
    }
}
