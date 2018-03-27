package com.herohoy.algorithms.factorial;

import com.herohoy.utils.tailrec.TailInvoke;
import com.herohoy.utils.tailrec.TailRecursion;

/** 测试代码（阶乘计算）
 * @author lihui  2018/3/22 下午5:17
 **/
public class Factorial {

    /**
     * 阶乘计算 -- 尾递归解决
     *
     * @param factorial 上一轮递归保存的值
     * @param number    当前阶乘需要计算的数值
     * @return number!
     */
    private static TailRecursion<Long> factorialTailRecursion(final long factorial , final long number) {
        if (number == 1)
            return TailInvoke.done(factorial);
        else
            return TailInvoke.call(() -> factorialTailRecursion(factorial + number, number - 1));
    }

    public static Long factorial(long factorial,long number) {
        factorial = factorial <= 0 ? 1 : factorial;
        number = number <= 0 ? 1 : number;
        return factorialTailRecursion(factorial,number).invoke();
    }
}
