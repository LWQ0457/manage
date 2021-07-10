package com.liuwq.hdfs.manage.pojo;

public class ResultBody {
    private String code;
    private String msg;
    private Object data;

    public ResultBody() {
    }

    public ResultBody(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public ResultBody(String code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static ResultBody success(Object data) {
        return new ResultBody(ResultEnum.SUCCESS.getCode(),
                ResultEnum.SUCCESS.getMsg(), data);
    }

    public static ResultBody error_noFind() {
        return new ResultBody(ResultEnum.NOT_FOUND.getCode(),
                ResultEnum.NOT_FOUND.getMsg(), null);
    }

    public static ResultBody error_serverBusy(Object data) {
        return new ResultBody(ResultEnum.SERVER_BUSY.getCode(),
                ResultEnum.SERVER_BUSY.getMsg(), data);
    }

    public static ResultBody error(BaseResultInfoInterface errorInfo) {
        return new ResultBody(errorInfo.getCode(),
                errorInfo.getMsg(), null);
    }

    public static ResultBody error(String code, String msg) {
        return new ResultBody(code,
                msg, null);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

}
