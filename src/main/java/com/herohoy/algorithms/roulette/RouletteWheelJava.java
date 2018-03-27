package com.herohoy.algorithms.roulette;

/**
 * 轮盘赌算法/比例选择
 */
public class RouletteWheelJava {
    public void rouletteTest() {
        double[] P={0.2,0.3,0.5};//假设的概率分布
        int appearnum=0;
        for(int i=0;i<100;i++){
            int result=roulette(P);
            if(result==2)//统计第2类对象生成的数目，可以看出大致为50%的生成概率
                appearnum++;
        }
        System.out.println(appearnum);
    }
    /**
     * 轮盘赌函数
     * @param P 各类对象概率分布
     * @return 生成的对象类
     */
    private int roulette(double[] P){

        double rand=Math.random();
        double pointer=0;//pointer指示每个区段的右边界，从左往右扫描判断
        for(int i=0;i<P.length;i++){
            pointer+=P[i];
            if(rand<=pointer)
                return i;
        }
        return -1;
    }
}
