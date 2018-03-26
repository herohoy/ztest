package com.herohoy;

import com.herohoy.fibonacci.Fibonacci;
import com.herohoy.tailrec.FactorialTailRecursion;
import com.today36524.print99.PrintNineNine;

/**
 * @author lihui  2018/3/22 下午5:35
 **/
public class Main {
    public static void main(String[] args){
        PrintNineNine n = new PrintNineNine();
        n.printNineNine(1,1,9,9);

//        TestErr te = new TestErr();
//        te.test1(1);
//        te.test1(2);


        // StackOverflowError
//        FactorialTailRecursion.factorialTailRecursionNonInterface(1,100000);
        System.out.println(FactorialTailRecursion.factorialTailRecursion(1L,10000000L).invoke());

        System.out.println(Fibonacci.fibonacci(70));
    }
}
