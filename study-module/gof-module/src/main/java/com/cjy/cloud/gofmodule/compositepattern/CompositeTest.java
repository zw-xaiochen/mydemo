package com.cjy.cloud.gofmodule.compositepattern;

public class CompositeTest {

    public static void main(String[] args) {
        AbstractTreeNode rootNode = new OrgNode("根节点", "0", "1", 0);


        AbstractTreeNode orgNode = new OrgNode("企业节点1", "1", "2", 0);
        // 分组构件(分组节点下不能再有分组节点)
        AbstractTreeNode orgGroupNode = new GroupNode("分组节点", "2", "123", 1);
        // 叶子-监控对象
        AbstractTreeNode monitor = new MonitorNode("监控对象节点", "123", "456789", 2);
        orgGroupNode.add(monitor);
        orgNode.add(orgGroupNode);


        AbstractTreeNode orgNode1 = new OrgNode("企业节点2", "1", "3", 0);
    }
}
