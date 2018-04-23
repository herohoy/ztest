package com.today36524.treeloop;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author lihui  2018/4/23 下午8:49
 **/
public class TreeNodeJava {
    private long id;
    private Optional<TreeNodeJava> parent = Optional.empty();
    private List<TreeNodeJava> childNodes = new ArrayList<TreeNodeJava>();

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Optional<TreeNodeJava> getParent() {
        return parent;
    }

    public void setParent(Optional<TreeNodeJava> parent) {
        this.parent = parent;
    }

    public List<TreeNodeJava> getChildNodes() {
        return childNodes;
    }

    public void setChildNodes(List<TreeNodeJava> childNodes) {
        this.childNodes = childNodes;
    }
}
