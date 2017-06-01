package org.com.zrhx.model;


import org.com.zrhx.enity.BaseEntity;

public class SysDictionaryLine extends BaseEntity<SysDictionaryLine>{
    private String lineID;

    private String headerCode;

    private String value;

    private Integer SN;

    private String lineName;

    private String defaultFlag;

    private String memo;



    public String getLineID() {
        return lineID;
    }

    public void setLineID(String lineID) {
        this.lineID = lineID == null ? null : lineID.trim();
    }

    public String getHeaderCode() {
        return headerCode;
    }

    public void setHeaderCode(String headerCode) {
        this.headerCode = headerCode == null ? null : headerCode.trim();
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value == null ? null : value.trim();
    }

    public Integer getSN() {
        return SN;
    }

    public void setSN(Integer SN) {
        this.SN = SN;
    }

    public String getLineName() {
        return lineName;
    }

    public void setLineName(String lineName) {
        this.lineName = lineName == null ? null : lineName.trim();
    }

    public String getDefaultFlag() {
        return defaultFlag;
    }

    public void setDefaultFlag(String defaultFlag) {
        this.defaultFlag = defaultFlag == null ? null : defaultFlag.trim();
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo == null ? null : memo.trim();
    }


}