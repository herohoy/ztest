package com.today36524.watertrap

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
  def trap(nums:Vector[Int]): Int = {
    (for(i <- 0 until nums.size) yield { (i,nums(i)) })
      .groupBy(_._2).keySet.toList.sortWith(_ < _).map(a => {
      val nonZeros = (for(i <- 0 until nums.size if nums(i) > a) yield { i })
      if(nonZeros.nonEmpty) { (nonZeros.last - nonZeros.head - 1) - (nonZeros.size - 2) } else { 0 }
    }).sum
  }
}
