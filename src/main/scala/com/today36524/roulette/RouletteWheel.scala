package com.today36524.roulette

/**
  * 轮盘赌算法/比例选择（scala代码暂时不正确，需要修复 TODO）
  */
class RouletteWheel {
  def roulette(P:Array[Double]): Int = {
    var result = -1
    var pointer:Double=0;//pointer指示每个区段的右边界，从左往右扫描判断
    for(i <- 0 until P.length){
      pointer = pointer + P(i)
      if(Math.random()<=pointer)
        result = i
    }
    result
  }

  def rouletteTest(P:Array[Double] = Array[Double](0.2,0.3,0.5)) = {
    var appearnum=0;
    for(i <- 0 until 100){
      if(roulette(P) == 2){  //统计第2类对象生成的数目，可以看出大致为50%的生成概率
        appearnum = appearnum + 1
      }
    }
    println(appearnum)
  }
}
/*

object RouletteWheel {
  def rouletteTest(P:Array[Double] = Array[Double](0.2,0.3,0.5)) = {
    var appearnum=0;
    for(i <- 0 to 100){
      if((new RouletteWheel).roulette(P) == 2)  //统计第2类对象生成的数目，可以看出大致为50%的生成概率
        appearnum = appearnum + 1;
    }
    println(appearnum)
  }
}
*/
