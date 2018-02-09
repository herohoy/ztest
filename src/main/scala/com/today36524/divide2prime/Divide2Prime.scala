package com.today36524.divide2prime

import scala.annotation.tailrec

/**
  * 分解质因数
  */
class Divide2Prime {
  @tailrec
  private def divide2Prime(n:Int = 2,m:Int = 2,res:StringBuilder = new StringBuilder):StringBuilder = {
    if(res.isEmpty) res.append(n).append("=")
    if(n<2 || m<2){
      res.append(n)
    }else if(n/m==1 && n%m==0){
      res.append(m)
    }else if(n%m==0){
      res.append(m).append("x")
      divide2Prime(n = n/m,res = res)
    }else{
      divide2Prime(n,m+1,res)
    }
  }

  def showDivide(n:Option[Int] = None): Unit = {
    println(divide2Prime(n = n.getOrElse(2)).toString)
  }
}
