package com.cjy.cloud.gofmodule.compositepattern;

import java.util.ArrayList;
import java.util.List;

public class GroupNode extends AbstractTreeNode {
    // 监控对象节点
    private final List<MonitorNode> monitorNodes = new ArrayList<>();

    public GroupNode(String name, String parentId, String id, Integer type) {
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
