package com.herohoy.fibonacci;

import com.herohoy.tailrec.TailInvoke;
import com.herohoy.tailrec.TailRecursion;

/**
 * @author lihui  2018/3/26 下午5:46
 **/
public class Fibonacci {
    private static TailRecursion<Long> fibonacciRec (int remain,final Long n1,final Long n2) {
        if(remain == 0){
            System.out.println(n1);
            return TailInvoke.done(n1);
        }else{
            System.out.println(n1);
            return TailInvoke.call(() -> fibonacciRec(remain - 1,n2,n1+n2));
        }
    }

    public static Long fibonacci(int remain){
        remain = remain < 0 ? 10 : remain;
        return fibonacciRec(remain,1L,1L).invoke();
    }
}
