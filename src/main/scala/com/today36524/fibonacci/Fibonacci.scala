package com.today36524.fibonacci

import scala.annotation.tailrec

/**
  * 斐波那契数 (古典兔子：1,1,2,3,5,8,13,21...)
  */
class Fibonacci {


  @tailrec
  private def fibonacciRec(remain: Int, n1: Long = 1, n2: Long = 1): Long = {
    remain match {
      case 0 => println(n1);n1
      case _ => println(n1)
        fibonacciRec(remain - 1, n2, n1 + n2)
    }
  }

  def fibonacci(remain:Int = 10): Long = {
    fibonacciRec(remain = remain)
  }

}
