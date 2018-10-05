package com.today36524.factorial

import scala.annotation.tailrec

/**
  *
  * @author lihui  2018/10/5 下午11:02
  **/
class Factorial {
  @tailrec
  private def factorialTailRecursion(factorial:Long = 1,number:Long = 1):Long = {
    if(number == 1){
      factorial
    }else{
      factorialTailRecursion(factorial + number , number - 1)
    }
  }

  def factorial(factorial: => Long = 1,number: => Long = 1): Long =
    factorialTailRecursion(
      if(factorial<1){1}else factorial
      ,if(number<1){1}else number
    )
}
