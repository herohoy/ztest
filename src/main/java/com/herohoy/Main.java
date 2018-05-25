package com.herohoy;

import com.herohoy.algorithms.factorial.Factorial;
import com.herohoy.algorithms.fibonacci.Fibonacci;
import com.herohoy.tools.treeloop.TreeLoopJava;
import com.herohoy.tools.treeloop.TreeNodeJava;
import com.herohoy.utils.tailrec.FactorialTailRecursion;
import com.today36524.print99.PrintNineNine;

import java.util.List;
import java.util.Optional;

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
        System.out.println();

        System.out.println(FactorialTailRecursion.factorialTailRecursion(1L,10000000L).invoke());
        System.out.println();

        System.out.println(Factorial.factorial(-1,50000L));
        System.out.println();

        System.out.println(Fibonacci.fibonacci(70));
        System.out.println();

        TreeLoopJava tj = new TreeLoopJava();
        List<TreeNodeJava> l = tj.getChildNodesRec(3L);
        System.out.println(l);
        System.out.println();

        Optional<TreeNodeJava> tn = tj.getNodeJavaWithParents(40L);
        System.out.println(tn);
        System.out.println();
    }
}
