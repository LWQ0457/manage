package com.liuwq.hdfs.manage.exception;

import com.liuwq.hdfs.manage.pojo.ResultBody;
import com.liuwq.hdfs.manage.pojo.ResultEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
@ControllerAdvice
public class GlobalExceptionHandler {
    private final static Logger logger= LoggerFactory.getLogger(GlobalExceptionHandler.class);
    @ExceptionHandler(value =Exception.class)
    public ResultBody exceptionHandler(HttpServletRequest req, Exception e){
        logger.error("未知异常！原因是:",e);
        return ResultBody.error(ResultEnum.INTERNAL_SERVER_ERROR);
    }
}