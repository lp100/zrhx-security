package org.com.zrhx.model;

import org.com.zrhx.enity.BaseEntity;

import java.util.Date;

public class SysDictionaryHeader extends BaseEntity<SysDictionaryHeader>{
    private String headerCode;

    private String headerName;

    private String dictionaryType;

    private String memo;



    public String getHeaderCode() {
        return headerCode;
    }

    public void setHeaderCode(String headerCode) {
        this.headerCode = headerCode == null ? null : headerCode.trim();
    }

    public String getHeaderName() {
        return headerName;
    }

    public void setHeaderName(String headerName) {
        this.headerName = headerName == null ? null : headerName.trim();
    }

    public String getDictionaryType() {
        return dictionaryType;
    }

    public void setDictionaryType(String dictionaryType) {
        this.dictionaryType = dictionaryType == null ? null : dictionaryType.trim();
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo == null ? null : memo.trim();
    }


}