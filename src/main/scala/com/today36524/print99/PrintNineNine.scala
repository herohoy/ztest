package com.today36524.print99

import scala.annotation.tailrec

/**
  * 九九乘法表
  */
class PrintNineNine {

  @tailrec
  final def printNineNine(x:Int = 1,y:Int = 1,xe:Int = 9,ye:Int = 9) : Unit = {
    if (x<=xe || y<=ye) {
      if (x<=xe){
        print((x*y) + " ")
        printNineNine(x+1,y,xe,ye)
      }else{
        println()
        printNineNine(y+1,y+1,xe,ye)
      }
    }
  }
}
