package com.today36524.print99

import scala.annotation.tailrec

/**
  * 九九乘法表
  */
class PrintNineNine {

  @tailrec
  final def printNineNine(x:Int = 1,y:Int = 1) : Unit = {
    if (x<=9 || y<=9) {
      if (x<=9){
        print((x+y) + " ")
        printNineNine(x+1,y)
      }else{
        println()
        printNineNine(y+1,y+1)
      }
    }
  }
}
