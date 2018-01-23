package com.today36524.fibonacci

import scala.annotation.tailrec

/**
  * 斐波那契数
  */
class Fibonacci {


  @tailrec
  final def fibonacci(remain: Int, n1: Long = 1, n2: Long = 1): Long = {
    remain match {
      case 0 => println(n1);n1
      case _ => println(n1)
        fibonacci(remain - 1, n2, n1 + n2)
    }
  }
}
