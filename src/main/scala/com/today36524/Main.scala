package com.today36524

import com.today36524.fibonacci.Fibonacci
import com.today36524.print99.PrintNineNine
import com.today36524.rws._

object Main {
  def main(args: Array[String]): Unit = {
    val nn = new PrintNineNine
    nn.printNineNine()
    nn.printNineNine(x=1,y=1,xe=32,ye=32)

    println()

    val fb = new Fibonacci
    fb.fibonacci(70)

    println()

    val rw =
//      new RouletteWheel   //scala代码有问题，每次值都是100
      new RouletteWheelJava  //java代码暂无问题
    rw.rouletteTest()
  }
}
