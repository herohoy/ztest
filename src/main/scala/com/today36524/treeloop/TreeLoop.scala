package com.today36524.treeloop

import java.sql.DriverManager

import com.sun.org.apache.xerces.internal.dom.ParentNode

import scala.annotation.tailrec
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
//    throw new RuntimeException
    TreeNode(id = id,childNodes = getChildNodes(id))
  }

//  private def getChildsRec(node:TreeNode): TreeNode = {
//    if(node.childNodes.isEmpty){
//      node
//    }else{
//      for(i <- 0 until childNodes.size) {
//        getChildsRec(id = childNodes(i).id,index = i,childNodes = getChildNodes(childNodes(i).id))
//      }
//    }
//  }

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
  private def getParentsRec(node:Option[TreeNode],parentNode: Option[TreeNode] = None):Option[TreeNode] = {
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
}
