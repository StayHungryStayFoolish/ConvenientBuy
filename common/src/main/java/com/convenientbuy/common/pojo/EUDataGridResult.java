package com.convenientbuy.common.pojo;

/**
 * Created by bonismo@hotmail.com
 * 下午10:04 on 16/8/18.
 *
 * EasyUI 树形控件节点[ 应用在后台 CMS 页面中 ]
 */
public class EUDataGridResult {
    private long id;
    private String text;
    private String state;
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
    }
    public String getState() {
        return state;
    }
    public void setState(String state) {
        this.state = state;
    }
}
