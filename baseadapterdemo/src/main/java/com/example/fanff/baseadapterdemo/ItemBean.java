package com.example.fanff.baseadapterdemo;

/**
 * 创建设置每个Item的类
 * Created by herd_youth on 2016/4/15.
 */
public class ItemBean {
    private int ItemImageResid;
    private String ItemTitle;
    private String ItemContent;

    public ItemBean(int itemImageResid, String itemTitle, String itemContent) {
        ItemImageResid = itemImageResid;
        ItemTitle = itemTitle;
        ItemContent = itemContent;
    }

    public int getItemImageResid() {
        return ItemImageResid;
    }

    public void setItemImageResid(int itemImageResid) {
        ItemImageResid = itemImageResid;
    }

    public String getItemTitle() {
        return ItemTitle;
    }

    public void setItemTitle(String itemTitle) {
        ItemTitle = itemTitle;
    }

    public String getItemContent() {
        return ItemContent;
    }

    public void setItemContent(String itemContent) {
        ItemContent = itemContent;
    }
}
