package com.today36524.gcdlcm

/**
  * 最大公约数 和 最小公倍数
  */
class GcdLcm {
  /**
    * 最大公约数
    */
  def greatestCommonDivisor(a:Int = 6,b:Int = 4): Int = {
    (for(i <- 1 to (if(a>b) b else a) if a%i==0 && b%i==0) yield { i }).last
  }

  /**
    * 最小公倍数
    */
  def leastCommonMultiple(a:Int = 6,b:Int = 4): Int = {
    (for(i <- 1 to (if(a>b) a else b) if a*i%b==0) yield { a*i }).head
  }

  def showGcdAndLcm(a:Int = 6,b:Int = 4): Unit = {
    println(s"""the Greatest Common Divisor of $a and $b is:"""+greatestCommonDivisor(a,b))
    println("the Least Common Multiple of "+a+" and "+b+" is:"+leastCommonMultiple(a,b))
  }
}
