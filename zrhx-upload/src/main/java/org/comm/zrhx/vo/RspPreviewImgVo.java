package org.comm.zrhx.vo;

/**
 * uediter 浏览图片对象
 * Created by lp on 2016/11/5.
 */
public class RspPreviewImgVo {

    private  boolean is_dir;
    private  boolean has_file;
    private  long filesize;
    private  boolean is_photo;
    private  String filetype;
    private  String filename;
    private  String datetime;


    public boolean is_dir() {
        return is_dir;
    }

    public void setIs_dir(boolean is_dir) {
        this.is_dir = is_dir;
    }

    public boolean isHas_file() {
        return has_file;
    }

    public void setHas_file(boolean has_file) {
        this.has_file = has_file;
    }

    public long getFilesize() {
        return filesize;
    }

    public void setFilesize(long filesize) {
        this.filesize = filesize;
    }

    public boolean is_photo() {
        return is_photo;
    }

    public void setIs_photo(boolean is_photo) {
        this.is_photo = is_photo;
    }

    public String getFiletype() {
        return filetype;
    }

    public void setFiletype(String filetype) {
        this.filetype = filetype;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }
}
