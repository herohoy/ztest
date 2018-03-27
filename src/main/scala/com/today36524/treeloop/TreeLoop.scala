package com.today36524.treeloop

import scala.annotation.tailrec

/**
  * 树节点遍历（单层遍历、首行遍历、全遍历）
  *
  * @author lihui  2018/3/27 上午11:24
  **/
class TreeLoop {

  //当前节点查询 (假设父节点默认查出)
  def getNode(id:Long): TreeNode = {
    TreeNode(id = id)
  }
  //单层子节点查询
  def getChildNodes(id:Long): List[TreeNode] = {
    getNode(id).childNodes
  }

  /**
   * 单层遍历
   * @return
   * @author lihui
   */
  def getNodeWithSingleChilds(id:Long): TreeNode = {
    TreeNode(id = id,childNodes = getChildNodes(id))
  }

  private def getTrees(id:Long,childNodes:List[TreeNode] = List[TreeNode]()) = {
    if(childNodes.isEmpty){
      getNode(id = id)
    }else{
      TreeNode(id = id)
    }
  }
}
