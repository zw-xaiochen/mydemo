package com.cjy.cloud.gofmodule.compositepattern;

import java.util.ArrayList;
import java.util.List;

public class OrgNode extends AbstractTreeNode {

    // 包含分组节点和企业节点
    private final List<AbstractTreeNode> childNode = new ArrayList<>();

    public OrgNode(String name, String parentId, String id, Integer type) {
        super(name, parentId, id, type);
    }

    @Override
    void add(AbstractTreeNode treeNode) {
        childNode.add(treeNode);
    }

    @Override
    List<AbstractTreeNode> getChild() {
        return null;
    }
}
