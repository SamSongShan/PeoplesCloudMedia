package com.example.a11355.peoplescloudmedia.base;


import com.example.a11355.peoplescloudmedia.model.EContent;

import java.io.Serializable;
import java.util.List;

/**
 * 结果回传值
 * Created by HDL on 2017/7/11.
 */

public class EditorResultBean implements Serializable {
    private List<EContent> contents;

    public List<EContent> getContents() {
        return contents;
    }

    public void setContents(List<EContent> contents) {
        this.contents = contents;
    }

    @Override
    public String toString() {
        return "EditorResultBean{" +
                "contents=" + contents +
                '}';
    }
}
