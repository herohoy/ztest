package com.today36524

import com.herohoy.algorithms.roulette.RouletteWheelJava
import com.today36524.divide2prime.Divide2Prime
import com.today36524.factorial.Factorial
import com.today36524.fibonacci.Fibonacci
import com.today36524.gcdlcm.GcdLcm
import com.today36524.narcissusnum.NarcissusNumber
import com.today36524.treeloop.TreeLoop
import com.today36524.watertrap.Trap

//import scala.collection.mutable.ListBuffer
//import com.today36524.other.User
import com.today36524.primenum.PrimenumOrNot
import com.today36524.print99.PrintNineNine

object Main {
  def main(args: Array[String]): Unit = {

    //九九乘法表及其扩展
    val nn = new PrintNineNine
    nn.printNineNine()
    nn.printNineNine(x=3,y=3,xe=32,ye=32)

    println()

    //斐波那契数列
    val fb = new Fibonacci
    fb.fibonacci(70)

    println()

    //轮盘赌算法
    val rw =
//      new RouletteWheel   //scala代码有问题，每次值都是100
      new RouletteWheelJava  //java代码暂无问题
    rw.rouletteTest()

    //列表类集合reduceLeft分解，可用于sql语句
    /*val rs = List("abfbf").reduceLeft(_+","+_)  // todo 只要有一条数据即不会报错
//    val rs2 = List().reduceLeft(_+","+_)  // fixme 空list不适用
    println(rs)*/

    println()

    //判断是否质数并打印区间内质数
    val nu = new PrimenumOrNot
    /*for(i <- 101 to 200){
      println(i+" is noun? "+nu.isPrimeNum(i))
    }*/
    nu.showPrimeNums(200,300)

    println()

    //水仙花数
    val narc = new NarcissusNumber
    narc.showAllNarcissusNum()


    println()

    //分解质因数
    val dv = new Divide2Prime
    dv.showDivide(Option(221))
    dv.showDivide(Option(247))
    dv.showDivide(Option(253))
    dv.showDivide(Option(259))
    dv.showDivide(Option(289))
    dv.showDivide(Option(299))

    //集合的diff相减
    //有效
//    val userlist = List[User](User(1,"steve",1),User(2,"micheal",1),User(3,"mary",2),User(4,"albert",1),User(5,"victoria",2))
//    val list1 = for(user <- userlist if user.sex==1) yield user
//    val list2 = userlist diff list1
//
//    println(list1)
//    println(list2)

    println()

    //最大公约数和最小公倍数
    val gl = new GcdLcm
    gl.showGcdAndLcm()
//    gl.showGcdAndLcm(4,23)
//    gl.showGcdAndLcm(15,9)
//    gl.showGcdAndLcm(22,20)


    println()

    //set化去重
//    val ls = ListBuffer[String]("1")
//    ls ++= List[String]("2","1","3")
//    println(ls.toSet)
//
//    println()

    //多元分组测试
//    case class User(name:String,male:Boolean = true,age:Int = 18)
//    case class Company(tel:String,address:String)
//    val today = Company(tel = "15566778899",address = "硚口越秀")
//    val ifly = Company(tel = "15678901234",address = "蜀山望江")
//    val list = List[(User,Company)](
//      (User(name = "yanke",age = 29),ifly),
//      (User(name = "lpt",age = 28),ifly),
//      (User(name = "xy",age = 26),today),
//      (User(name = "lhz",age = 25),today),
//      (User(name = "chenmm",male = false),ifly),
//      (User(name = "zhj",male = false),today)
//    )
//    println(list.groupBy(topic => (topic._1.male,topic._2.address)))

//    val te = new TestErr
//    te.test1(1)
//    te.test1(2)

    // Option和Some的判断测试
//    val a = Some(null)
//    if(a.isDefined) {
//      println("!")
//      println(a)
//    }
//    println(Option(null).isDefined) //结果为false
//    println(Some(null).isDefined) //结果为true

    //坐标方块储水
    val t = new Trap
    println(t.trap(Vector(0,1,0,2,1,3,1,0,1,2,0,1)))

    println()
/*
    // 尝试s字符串编写sql语句
    val id:Int = 1
    val sql: String =
      s"""
         select * from tab
         where id = ${id}
       """
    println(sql)
    */

    val fac:Factorial = new Factorial
    println(fac.factorial(2,2) +":"
      + com.herohoy.algorithms.factorial.Factorial.factorial(2,2))

    println()

    //显示treeNode以及测试递归
    val tree:TreeLoop = new TreeLoop
    println(tree.getNode(1))
    println(tree.getNodeWithSingleParent(10))
    println(tree.getChildNodes(1))
    println(tree.getNodeWithParents(40))

    val res = tree.getNodeJavaWithParents(40)
    println(res)

    println(tree.getNodeWithSingleChilds(1))
    println(tree.getNodeWithSingleChilds(40))

    val resc = tree.getNodeJavaWithAllChilds(1)
    val resf = tree.getNodeJavaWithAllChilds(3)
    println(resc)

  }


}
