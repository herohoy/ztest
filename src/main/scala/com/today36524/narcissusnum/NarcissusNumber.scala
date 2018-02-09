package com.today36524.narcissusnum

/**
  * 水仙花数
  */
class NarcissusNumber {
  def isNarcissusOrNot(n:Int): Boolean = {
    if(n<100 || n>999){
      false
    }else{
      n == Math.pow(n/100,3).toInt + Math.pow(n%100/10,3).toInt + Math.pow(n%10,3).toInt
    }
  }

  def showAllNarcissusNum(begin:Int = 100,end:Int = 999): Unit = {
    val res:StringBuilder = new StringBuilder
    for(i <- begin to end){
      if(isNarcissusOrNot(i)){
        res.append(i)
        res.append(" ")
      }
    }
    println(res.toString)
  }
}
