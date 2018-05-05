package com.today36524.treeloop

/**
  *
  * @author lihui  2018/3/27 上午11:21
  **/
case class TreeNode(
                     id:Long,
                     name:Option[String] = None,
                     parent:Option[TreeNode] = None,
                     childNodes:List[TreeNode] = List[TreeNode]()
                   )
