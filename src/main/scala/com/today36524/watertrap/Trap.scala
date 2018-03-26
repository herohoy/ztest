package com.today36524.watertrap

import scala.annotation.tailrec
import scala.collection.mutable.ListBuffer

/**
  * 平面坐标(x轴y轴组成的二维坐标)方块储水问题计算
  *
  * @author lihui  2018/3/15 下午5:25
  **/
class Trap {
  /**
   * 方块储水
    * 思路进化过程：
    * 1、先思考的是从大到小取范围再减去范围内的y值
    * 2、后来发现可以从小到大拼出空格数并减去范围内超出的y值，再将每行的空格数相加
   * @return
   * @author lihui
   */
  def trap(nums:Vector[Int]) = {
    (for(i <- 0 until nums.size) yield { (i,nums(i)) })
      .groupBy(_._2).keySet.toList.sortWith(_ < _).map(a => {
      val nonZeros = (for(i <- 0 until nums.size if nums(i) > a) yield { i })
      if(nonZeros.nonEmpty) { (nonZeros.last - nonZeros.head - 1) - (nonZeros.size - 2) } else { 0 }
    }).sum
  }
}

/*

case class TreeNode(id:Long,parent:Option[TreeNode] = None,childNodes:List[TreeNode] = List[TreeNode]())

class TreeNodeUtil{
  //当前节点查询 (假设父节点默认查出)
  def getNode(id:Long): TreeNode = {
    TreeNode(id = id)
  }
  //单层子节点查询
  def getChildNodes(id:Long): List[TreeNode] = {
    getNode(id).childNodes
  }

  @tailrec
  private def getChildNodesTailrec(node: TreeNode):List[TreeNode] = {
    if(node.childNodes.isEmpty){
      node.childNodes
    }else{
      node.childNodes.map(nd => {
        getChildNodesTailrec(nd)
      })
    }
  }


}
*/

/*

object Trap{
  def main(args: Array[String]): Unit = {
    val t = new Trap
    println(t.trap(Vector(0,1,0,2,1,3,1,0,1,2,0,1)))
//    println(t.trap(Vector(0,0)))
  }
}
*/
