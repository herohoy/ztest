package com.today36524

import com.today36524.fibonacci.Fibonacci
import com.today36524.narcissusnum.NarcissusNumber
import com.today36524.primenum.PrimenumOrNot
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

    /*val rs = List("abfbf").reduceLeft(_+","+_)
//    val rs2 = List().reduceLeft(_+","+_)  // todo 空list不适用
    println(rs)*/

    println()

    val nu = new PrimenumOrNot
    /*for(i <- 101 to 200){
      println(i+" is noun? "+nu.isPrimeNum(i))
    }*/
    nu.showPrimeNums(101,200)

    println()

//    val n = 123;
//    println(n/100)
//    println(n%100/10)
//    println(n%10)
    val narc = new NarcissusNumber
    narc.showAllNarcissusNum()
  }
}
