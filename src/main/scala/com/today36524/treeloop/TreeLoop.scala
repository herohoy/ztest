package com.today36524.treeloop

import java.sql.DriverManager
import java.util.Optional

import com.sun.org.apache.xerces.internal.dom.ParentNode

import scala.annotation.tailrec
import scala.collection.JavaConverters
import scala.collection.mutable.ListBuffer
//import com.mysql.jdbc.Driver

/**
  * 树节点遍历（单层遍历、首行遍历、全遍历）
  *
  * @author lihui  2018/3/27 上午11:24
  **/
class TreeLoop {
  Class.forName("com.mysql.jdbc.Driver")

  val conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/tree?useUnicode=true&characterEncoding=utf8",
    "root","today-36524")

  /**
   * 当前节点查询 (假设父节点默认查出)
   * @return
   * @author lihui
   */
  def getNode(id:Long): Option[TreeNode] = {
    val pst = conn.prepareStatement(
      s"""
         select * from tree_node where id=?
       """.stripMargin)
    pst.setLong(1,id)
    val rs = pst.executeQuery()
    rs.last
//    println(rs.getRow)
    if(rs.getRow == 1)
      Option(TreeNode(id = rs.getLong("id")))
    else
      None
  }

  /**
    * 单层父节点查询
    * @param id
    * @return
    */
  def getNodeWithSingleParent(id:Long): Option[TreeNode] = {
    val pst = conn.prepareStatement(
      s"""
         select * from tree_node where id=?
       """.stripMargin)
    pst.setLong(1,id)
    val rs = pst.executeQuery()
    rs.last
    if(rs.getRow == 1)
      Option(TreeNode(id = rs.getLong("id"),parent = getNode(rs.getLong("parent_id"))))
    else
      None
  }

  def getNodeWithParents(id:Long): Option[TreeNode] = {
    val node = getNodeWithSingleParent(id)
    if(node.isEmpty)
      node
    else
      getParentsRec(node = node,parentNode = node.get.parent)
  }

  /**
    * 获取节点，并按层级获取所有父节点（尾递归暂不适用）
    * @param node
    * @param parentNode
    * @return
    */
  //  @tailrec
  def getParentsRec(node:Option[TreeNode],parentNode: Option[TreeNode] = None):Option[TreeNode] = {
    if(node.isEmpty)
      node
    else if(parentNode.isEmpty)
      getNode(node.get.id)
    else {
      val thisnode = getNodeWithSingleParent(node.get.id)
      Option(TreeNode(id = thisnode.get.id,
        parent = getParentsRec(node = thisnode.get.parent,parentNode = getNode(parentNode.get.id))))
    }
  }



  /**  结合java代码，使用不完美的尾递归（不符合函数式编程规范）完成树的父节点全获取  */
  private var currentNode:Optional[TreeNodeJava] = Optional.empty()

  @tailrec
  private def getParentsTailrec(node:Optional[TreeNodeJava]):Optional[TreeNodeJava] = {
    if(!node.get.getParent.isPresent) {
      currentNode
    } else {
      val parentNode = getNodeWithSingleParent(node.get().getParent.get().getId)
      var parentNodeJava = new TreeNodeJava
      parentNodeJava.setId(parentNode.get.id)
      if(parentNode.get.parent.nonEmpty){
        var njParent = new TreeNodeJava
        njParent.setId(parentNode.get.parent.get.id)
        parentNodeJava.setParent(Optional.of(njParent))
      }
      node.get.setParent(Optional.of(parentNodeJava))
      getParentsTailrec(node.get.getParent)
    }
  }

  def getNodeJavaWithParents(id:Long): Optional[TreeNodeJava] ={
    currentNode = Optional.empty()
    val node = getNodeWithSingleParent(id)
    if(node.nonEmpty){
      var nodeJava = new TreeNodeJava
      nodeJava.setId(node.get.id)
      if(node.get.parent.nonEmpty){
        var njParent = new TreeNodeJava
        njParent.setId(node.get.parent.get.id)
        nodeJava.setParent(Optional.of(njParent))
      }
      currentNode = Optional.of(nodeJava)
    }
    getParentsTailrec(currentNode)
  }
  /**  end 结合java代码，使用不完美的尾递归（不符合函数式编程规范）完成树的父节点全获取  */


  /**
    * 单层子节点查询
    * @param id
    * @return
    */
  def getChildNodes(id:Long): List[TreeNode] = {
    val pst = conn.prepareStatement(
      s"""
         select * from tree_node where parent_id=?
       """.stripMargin)
    pst.setLong(1,id)
    val rs = pst.executeQuery()
    val childs:ListBuffer[TreeNode] = ListBuffer[TreeNode]()
    while (rs.next()){
      childs append TreeNode(id = rs.getLong("id"))
    }
    childs.toList
  }

  /**
    * 单层遍历
    * @return
    * @author lihui
    */
  def getNodeWithSingleChilds(id:Long): TreeNode = {
    TreeNode(id = id,childNodes = getChildNodes(id))
  }

}
