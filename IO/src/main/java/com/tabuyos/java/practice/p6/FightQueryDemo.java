package com.tabuyos.java.practice.p6;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @Author Tabuyos
 * @Time 2/29/20 9:04 PM
 * @Site www.tabuyos.com
 * @Email tabuyos@outlook.com
 * @Description
 */
@SuppressWarnings("AlibabaAvoidManuallyCreateThread")
public class FightQueryDemo {

    private static List<String> company = Arrays.asList("东方航空", "南方航空", "海南航空");
    private static List<String> fightList = new ArrayList<>();

    public static void main(String[] args) throws InterruptedException {
        String origin = "BJ";
        String dest = "SH";
        Thread[] threads = new Thread[company.size()];
        CountDownLatch countDownLatch = new CountDownLatch(company.size());
        for (int i = 0; i < threads.length; i++) {
            String name = company.get(i);
            threads[i] = new Thread(() -> {
                System.out.printf("%s查询从%s到%s的机票\n", name, origin, dest);
                int value = new Random().nextInt(10);
                try {
                    TimeUnit.SECONDS.sleep(value);
                    fightList.add(name + "--" + value);
                    System.out.printf("%s公司查询成功\n", name);
                    countDownLatch.countDown();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            threads[i].start();
        }
        countDownLatch.await();
        System.out.println("==========================查询结果如下============================");
        fightList.forEach(System.out::println);
    }

}
