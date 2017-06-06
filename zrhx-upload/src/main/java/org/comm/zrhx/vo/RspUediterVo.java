package org.comm.zrhx.vo;

/**
 * Created by lp on 2016/11/13.
 */
public class RspUediterVo {

    private  String state;

    private  String url;

    private  Long size;

    private  String original;

    private  String  type;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public String getOriginal() {
        return original;
    }

    public void setOriginal(String original) {
        this.original = original;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public RspUediterVo(String state, String url, Long size, String original, String type) {
        this.state = state;
        this.url = url;
        this.size = size;
        this.original = original;
        this.type = type;
    }

    public RspUediterVo(String state) {
        this.state = state;
    }
    public static  RspUediterVo success(String url, Long size, String original, String  type){
        return new RspUediterVo("SUCCESS", url,  size,  original,   type);
    }
    public static  RspUediterVo error(){
       return new RspUediterVo("ERROR");
    }

}
