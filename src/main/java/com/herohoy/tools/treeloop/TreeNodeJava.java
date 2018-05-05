package com.herohoy.tools.treeloop;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author lihui  2018/4/23 下午8:49
 **/
public class TreeNodeJava {
    private long id;
    private Optional<String> name = Optional.empty();
    private Optional<TreeNodeJava> parent = Optional.empty();
    private ArrayList<TreeNodeJava> childNodes = new ArrayList<TreeNodeJava>();


    public TreeNodeJava(){
        super();
    }

    public TreeNodeJava(long id){
        super();
        this.setId(id);
    }

    public TreeNodeJava(long id,Optional<String> name){
        super();
        this.setId(id);
        this.setName(name);
    }

    public TreeNodeJava(long id,Optional<String> name,Optional<TreeNodeJava> parent){
        super();
        this.setId(id);
        this.setName(name);
        this.setParent(parent);
    }

    public TreeNodeJava(long id,Optional<String> name,Optional<TreeNodeJava> parent,ArrayList<TreeNodeJava> childNodes){
        super();
        this.setId(id);
        this.setName(name);
        this.setParent(parent);
        this.setChildNodes(childNodes);
    }

    public TreeNodeJava(long id,String name){
        super();
        this.setId(id);
        this.setName(Optional.of(name));
    }

    public TreeNodeJava(long id,String name,TreeNodeJava parent){
        super();
        this.setId(id);
        this.setName(Optional.of(name));
        this.setParent(Optional.of(parent));
    }

    public TreeNodeJava(long id,String name,TreeNodeJava parent,ArrayList<TreeNodeJava> childNodes){
        super();
        this.setId(id);
        this.setName(Optional.of(name));
        this.setParent(Optional.of(parent));
        this.setChildNodes(childNodes);
    }


    public TreeNodeJava(long id,String name,ArrayList<TreeNodeJava> childNodes){
        super();
        this.setId(id);
        this.setName(Optional.of(name));
        this.setChildNodes(childNodes);
    }

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

    public ArrayList<TreeNodeJava> getChildNodes() {
        return childNodes;
    }

    public void setChildNodes(ArrayList<TreeNodeJava> childNodes) {
        this.childNodes = childNodes;
    }

    public Optional<String> getName() {
        return name;
    }

    public void setName(Optional<String> name) {
        this.name = name;
    }
}
