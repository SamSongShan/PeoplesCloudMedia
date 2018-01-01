package com.example.a11355.peoplescloudmedia.model;

/**
 * Created by 11355 on 2017/12/30.
 */

public class ZMTZZLink {

    private String LinkName;
    private String ToLink;
    private String AttachLinkId;
    private String SortCode;
    private String isDelete;


    public ZMTZZLink(String linkName, String toLink) {
        LinkName = linkName;
        ToLink = toLink;
        AttachLinkId = "default";
        SortCode = "1";
        isDelete = "0";
    }

    public ZMTZZLink(GetUserProductInfoEntity.DataEntity.AttachLinkListEntity data) {
        LinkName = data.getLinkName();
        ToLink = data.getToLink();
        AttachLinkId = data.getAttachLinkId();
        SortCode = data.getSortCode()+"";
        isDelete = "0";
    }

    public String getAttachLinkId() {
        return AttachLinkId;
    }

    public void setAttachLinkId(String attachLinkId) {
        AttachLinkId = attachLinkId;
    }

    public String getSortCode() {
        return SortCode;
    }

    public void setSortCode(String sortCode) {
        SortCode = sortCode;
    }

    public String getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(String isDelete) {
        this.isDelete = isDelete;
    }


    public String getLinkName() {
        return LinkName;
    }

    public void setLinkName(String linkName) {
        LinkName = linkName;
    }

    public String getToLink() {
        return ToLink;
    }

    public void setToLink(String toLink) {
        ToLink = toLink;
    }


}
