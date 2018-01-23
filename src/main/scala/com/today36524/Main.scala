package com.today36524

import com.today36524.fibonacci.Fibonacci
import com.today36524.print99.PrintNineNine

object Main {
  def main(args: Array[String]): Unit = {
    val nn = new PrintNineNine
    nn.printNineNine()

    println()

    val fb = new Fibonacci
    fb.fibonacci(50)
  }
}
