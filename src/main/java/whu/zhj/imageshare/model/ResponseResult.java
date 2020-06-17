package whu.zhj.imageshare.model;

import com.sun.org.apache.regexp.internal.RE;
import org.omg.CORBA.OBJ_ADAPTER;

import java.util.HashMap;

/**
 * @program: ImageShare
 * @description: 返回结果实体
 * @author: 周宏俊
 * @create: 2020-05-30 15:57
 */
public class ResponseResult extends HashMap<String, Object> {

    private static final long serialVersionUID = 1L;

    public static final String CODE_TAG = "code";

    public static final String MSG_TAG = "msg";

    public static final String DATA_TAG = "data";

    public enum Type{
        /** 成功*/
        SUCCESS(0),
        /** 失败*/
        FAIL(1),
        /** 警告*/
        WARN(301),
        /** 错误*/
        ERROR(500);

        private final int value;
        Type(int value){
            this.value = value;
        }

        public int getValue(){
            return this.value;
        }
    }

    /** 状态类型*/
    private Type type;

    /** 状态码*/
    private int code;

    /** 返回内容*/
    private String msg;

    /** 数据对象*/
    private Object data;

    public ResponseResult(){

    }

    public ResponseResult(Type type, String msg){
        super.put(CODE_TAG, type.value);
        super.put(MSG_TAG, msg);
    }

    public ResponseResult(Type type, String msg, Object data){
        this(type, msg);
        super.put(DATA_TAG, data);
    }

    /**
     * 返回成功消息
     * @return 成功消息
     */
    public static ResponseResult success(){
        return ResponseResult.success("操作成功");
    }

    /**
     * 返回成功消息
     * @param msg 返回内容
     * @return 成功消息
     */
    public static ResponseResult success(String msg){
        return ResponseResult.success(msg, null);
    }

    /**
     * 返回成功消息
     * @param msg 返回内容
     * @param data 返回数据
     * @return 成功消息
     */
    public static ResponseResult success(String msg, Object data){
        return new ResponseResult(Type.SUCCESS, msg, data);
    }

    /**
     * 返回失败消息
     * @return 失败消息
     */
    public static ResponseResult fail(){
        return ResponseResult.fail("操作失败");
    }

    /**
     * 返回失败消息
     * @param msg 返回内容
     * @return 失败消息
     */
    public static ResponseResult fail(String msg){
        return ResponseResult.fail(msg, null);
    }

    /**
     * 返回失败消息
     * @param msg 返回内容
     * @param data 返回数据
     * @return 失败消息
     */
    public static ResponseResult fail(String msg, Object data){
        return new ResponseResult(Type.FAIL, msg, data);
    }

    /**
     * 返回警告消息
     * @param msg 返回内容
     * @return 警告消息
     */
    public static ResponseResult warn(String msg){
        return ResponseResult.warn(msg, null);
    }

    /**
     * 返回警告消息
     * @param msg 返回内容
     * @param data 返回数据
     * @return 警告消息
     */
    public static ResponseResult warn(String msg, Object data){
        return new ResponseResult(Type.WARN, msg, data);
    }

    /**
     * 返回失败消息
     * @return 失败消息
     */
    public static ResponseResult error(){
        return ResponseResult.error("操作失败");
    }

    /**
     * 返回失败消息
     * @param msg 返回内容
     * @return 失败消息
     */
    public static ResponseResult error(String msg){
        return ResponseResult.error(msg, null);
    }

    /**
     * 返回失败消息
     * @param msg 返回内容
     * @param data 返回数据
     * @return 失败消息
     */
    public static ResponseResult error(String msg, Object data){
        return new ResponseResult(Type.ERROR, msg, data);
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
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
