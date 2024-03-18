package com.cjy.cloud.gofmodule.compositepattern;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public abstract class AbstractTreeNode {

    private String name;

    private String parentId;

    private String id;

    private Integer type;

    public AbstractTreeNode(String name, String parentId, String id, Integer type) {
        this.name = name;
        this.parentId = parentId;
        this.id = id;
        this.type = type;
    }

    void add(AbstractTreeNode treeNode){
        throw new UnsupportedOperationException();
    }

    List<AbstractTreeNode> getChild() {
        return new ArrayList<>();
    }
}
