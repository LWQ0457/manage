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
    public static ResultBody seccess(Object data){
        ResultBody resultBody=new ResultBody();
        resultBody.setCode(ResultEnum.SUCCESS.getCode());
        resultBody.setMsg(ResultEnum.SUCCESS.getMsg());
        resultBody.setData(data);
        return resultBody;
    }
    public static ResultBody server_busy(Object data){
        ResultBody resultBody=new ResultBody();
        resultBody.setCode(ResultEnum.SERVER_BUSY.getCode());
        resultBody.setMsg(ResultEnum.SERVER_BUSY.getMsg());
        resultBody.setData(data);
        return resultBody;
    }
    public static ResultBody error(BaseResultInfoInterface errorInfo){
        ResultBody resultBody=new ResultBody();
        resultBody.setCode(errorInfo.getCode());
        resultBody.setMsg(errorInfo.getMsg());
        resultBody.setData(null);
        return resultBody;
    }
    public static ResultBody error(String code ,String msg){
        ResultBody resultBody=new ResultBody();
        resultBody.setCode(code);
        resultBody.setMsg(msg);
        resultBody.setData(null);
        return resultBody;
    }

}
