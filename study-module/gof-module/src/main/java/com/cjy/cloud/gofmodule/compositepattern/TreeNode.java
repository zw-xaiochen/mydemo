package com.cjy.cloud.gofmodule.compositepattern;

import java.util.ArrayList;
import java.util.List;

public class TreeNode extends AbstractTreeNode {

    List<AbstractTreeNode> childNode = new ArrayList<>();

    public TreeNode(String name, String parentId, String id, Integer type) {
        super(name, parentId, id, type);
    }

    @Override
    void add(AbstractTreeNode treeNode) {

    }

    @Override
    List<AbstractTreeNode> getChild() {
        return null;
    }
}
