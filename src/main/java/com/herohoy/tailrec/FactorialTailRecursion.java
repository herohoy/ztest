package com.herohoy.tailrec;

/**
 * @author lihui  2018/3/22 下午5:17
 **/
public class FactorialTailRecursion {
//    /**
//     * 阶乘计算 -- 使用尾递归接口完成
//     * @param factorial 当前递归栈的结果值
//     * @param number 下一个递归需要计算的值
//     * @return 尾递归接口,调用invoke启动及早求值获得结果
//     */
//    public static TailRecursion<Integer> factorialTailRecursion(final int factorial, final int number) {
//        if (number == 1)
//            return TailInvoke.done(factorial);
//        else
//            return TailInvoke.call(() -> factorialTailRecursion(factorial + number, number - 1));
//    }

    /**
     * 阶乘计算 -- 尾递归(非lambda接口)解决
     *
     * @param factorial 上一轮递归保存的值
     * @param number    当前阶乘需要计算的数值
     * @return number!
     */
    @Deprecated
    public static int factorialTailRecursionNonInterface(final int factorial, final int number) {
        if (number == 1) return factorial;
        else return factorialTailRecursionNonInterface(factorial * number, number - 1);
    }

    /**
     * 阶乘计算 -- 尾递归解决
     *
     * @param factorial 上一轮递归保存的值
     * @param number    当前阶乘需要计算的数值
     * @return number!
     */
    public static TailRecursion<Long> factorialTailRecursion(final long factorial , final long number) {
        if (number == 1)
            return TailInvoke.done(factorial);
        else
            return TailInvoke.call(() -> factorialTailRecursion(factorial + number, number - 1));
    }
}
