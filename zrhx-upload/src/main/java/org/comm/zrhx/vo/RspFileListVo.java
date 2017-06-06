package org.comm.zrhx.vo;

import java.util.List;

/**
 * Created by lp on 2016/11/5.
 */
public class RspFileListVo {
    private  Integer total_count;
    private  String moveup_dir_path;
    private  String current_dir_path;
    private  String current_url;
    private List<RspPreviewImgVo> file_list;


    public Integer getTotal_count() {
        return total_count;
    }

    public void setTotal_count(Integer total_count) {
        this.total_count = total_count;
    }

    public String getMoveup_dir_path() {
        return moveup_dir_path;
    }

    public void setMoveup_dir_path(String moveup_dir_path) {
        this.moveup_dir_path = moveup_dir_path;
    }

    public String getCurrent_dir_path() {
        return current_dir_path;
    }

    public void setCurrent_dir_path(String current_dir_path) {
        this.current_dir_path = current_dir_path;
    }

    public String getCurrent_url() {
        return current_url;
    }

    public void setCurrent_url(String current_url) {
        this.current_url = current_url;
    }

    public List<RspPreviewImgVo> getFile_list() {
        return file_list;
    }

    public void setFile_list(List<RspPreviewImgVo> file_list) {
        this.file_list = file_list;
    }
}
