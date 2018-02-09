package com.today36524.primenum

import scala.annotation.tailrec

/**
  * 判断是否为素数(质数)
  */
class PrimenumOrNot {
  @tailrec
  final def isPrimeNum(n:Int,m:Int = 2):Boolean = {
    if(n<2 || (n%m==0 && n!=m)){
      false
    }else if(m>=n){
      true
    }else{
      isPrimeNum(n,m+1)
    }
  }

  def showPrimeNums(a:Int = 0,b:Int = 100): Unit = {
    val begin = if(a<=b) a else b
    val end = if(a>b) a else b
    val res:StringBuilder = new StringBuilder
    for(i <- begin to end if isPrimeNum(i))
      res.append(i).append(" ")
    println(res.toString)
  }
}
