package com.today36524.treeloop

import java.sql.{Connection, DriverManager}
import java.util.ArrayList
import java.util.Optional

import com.herohoy.tools.treeloop.TreeNodeJava

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

  val conn: Connection =
    DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/tree?useUnicode=true&characterEncoding=utf8",
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
      Option(TreeNode(id = rs.getLong("id"),name = Option (rs.getString("name"))))
    else
      None
  }

  /**
    * 单层父节点查询
    * @param id unique
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
      Option(TreeNode(id = rs.getLong("id"),name = Option( rs.getString("name")),
        parent = getNode(rs.getLong("parent_id"))))
    else
      None
  }

  @deprecated
  def getNodeWithParents(id:Long): Option[TreeNode] = {
    val node = getNodeWithSingleParent(id)
    if(node.isEmpty)
      node
    else
      getParentsRec(node = node,parentNode = node.get.parent)
  }

  /**
    * 递归获取节点，并按层级获取所有父节点（尾递归暂不适用）
    * @param node current
    * @param parentNode parent
    * @return
    */
  @deprecated
  //  @tailrec
  private def getParentsRec(node:Option[TreeNode],parentNode: Option[TreeNode] = None):Option[TreeNode] = {
    if(node.isEmpty)
      node
    else if(parentNode.isEmpty)
      getNode(node.get.id)
    else {
      val thisnode = getNodeWithSingleParent(node.get.id)
      Option(TreeNode(id = thisnode.get.id,name = thisnode.get.name,
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
      parentNodeJava.setName(Optional.of(parentNode.get.name.get))
      if(parentNode.get.parent.nonEmpty){
        var njParent = new TreeNodeJava
        njParent.setId(parentNode.get.parent.get.id)
        njParent.setName(Optional.of(parentNode.get.parent.get.name.get))
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
      nodeJava.setName(Optional.of(node.get.name.get))
      if(node.get.parent.nonEmpty){
        var njParent = new TreeNodeJava
        njParent.setId(node.get.parent.get.id)
        njParent.setName(Optional.of(node.get.parent.get.name.get))
        nodeJava.setParent(Optional.of(njParent))
      }
      currentNode = Optional.of(nodeJava)
    }
    getParentsTailrec(currentNode)
  }
  /**  end 结合java代码，使用不完美的尾递归（不符合函数式编程规范）完成树的父节点全获取  */


  /**
    * 单层子节点查询
    * @param id unique
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
      childs append TreeNode(id = rs.getLong("id"),name = Option( rs.getString("name")),
        parent = Option(TreeNode(id = id)))
    }
    childs.toList
  }

  /**
    * 单层遍历
    * @return
    * @author lihui
    */
  def getNodeWithSingleChilds(id:Long): Option[TreeNode] = {
    val node = getNode(id = id)
    if(node.isEmpty)
      None
    else
      Option(TreeNode(id = node.get.id,name = node.get.name,childNodes = getChildNodes(node.get.id)))
  }

  /**
    * 单层父子遍历
    * @param id
    * @return
    */
  def getNodeWithParentAndChilds(id:Long):Option[TreeNode] = {
    val node = getNodeWithSingleParent(id = id)
    if(node.isEmpty)
      None
    else
      Option(TreeNode(id = id,name = node.get.name,parent = node.get.parent,
        childNodes = getChildNodes(id)))
  }


  private var childIndex:Int = 0

  @tailrec
  private def getChildsTailrec(node:Optional[TreeNodeJava],index:Int):Optional[TreeNodeJava] = {
    if(!node.isPresent){
      childIndex = 0
      currentNode
    }
    //当到达叶子节点之后的操作（后续应继续下一个同级节点的查询和判断） （暂时有问题需要修改）
    else if(node.get.getChildNodes.isEmpty &&
      (node.get().getParent.isPresent
//        || (index>0 && index<node.get().getChildNodes.size())
      )
    ) {
      childIndex += 1
//      val nodeWithChilds = getNodeWithParentAndChilds(node.get().getParent.get().getId)
      getChildsTailrec(Optional.of(
        new TreeNodeJava(node.get.getParent.get.getId,node.get.getParent.get.getName,
          node.get.getParent.get.getParent
//          ,node.get.getParent.get.getChildNodes //放开子节点的填入，则会进入无限循环
        )),childIndex)
    } else if(index < node.get.getChildNodes.size){
      val nodeWithChilds = getNodeWithParentAndChilds(node.get().getChildNodes.get(index).getId)
      var childNodeJava = new TreeNodeJava(nodeWithChilds.get.id,nodeWithChilds.get.name.get,
        new TreeNodeJava(nodeWithChilds.get.parent.get.id,nodeWithChilds.get.parent.get.name.get,
          {
            var tmList:ArrayList[TreeNodeJava] = new ArrayList[TreeNodeJava]
            getChildNodes(nodeWithChilds.get.parent.get.id).foreach(tmp =>
              tmList.add(new TreeNodeJava(tmp.id,tmp.name.get)))
            tmList
          }))
      if(nodeWithChilds.get.childNodes.nonEmpty){
        var temp2:TreeNodeJava = new TreeNodeJava
        var tmpList:ArrayList[TreeNodeJava] = new ArrayList[TreeNodeJava]()
        nodeWithChilds.get.childNodes.map(temp => {
          temp2 = new TreeNodeJava(temp.id,temp.name.get)
          temp2.setParent(Optional.of(new TreeNodeJava(nodeWithChilds.get.id,nodeWithChilds.get.name.get)))
          tmpList.add(temp2)
        })
        childNodeJava.setChildNodes(tmpList)
      }
      node.get.getChildNodes.set(index,childNodeJava)
      getChildsTailrec(Optional.of(childNodeJava),
          {
            childIndex = 0
            childIndex
          }
      )
    } else {
      childIndex = 0
      currentNode
    }
  }

  def getNodeJavaWithAllChilds(id:Long):Optional[TreeNodeJava] ={
    currentNode = Optional.empty()
    childIndex = 0
    val node = getNodeWithSingleChilds(id)
    if(node.nonEmpty){
      var nodeJava = new TreeNodeJava(node.get.id,node.get.name.get)
//      nodeJava.setParent(Optional.of(new TreeNodeJava(node.get.id)))
      if(node.get.childNodes.nonEmpty){
        var temp2:TreeNodeJava = new TreeNodeJava
//        nodeJava.setChildNodes(
//          JavaConverters.seqAsJavaList(
//            node.get.childNodes.map(temp => {
//              temp2 = new TreeNodeJava(temp.id,temp.name.get)
//              temp2
//            })
//          )
//        )
        var tmpList:ArrayList[TreeNodeJava] = new ArrayList[TreeNodeJava]()
        node.get.childNodes.map(temp => {
          temp2 = new TreeNodeJava(temp.id,temp.name.get)
          temp2.setParent(Optional.of(new TreeNodeJava(id,node.get.name.get)))
          tmpList.add(temp2)
        })
        nodeJava.setChildNodes(tmpList)

      }
      currentNode = Optional.of(nodeJava)
    }
    getChildsTailrec(currentNode,childIndex)
  }

}
