package com.packt.tfesenko.multithreading.section1;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.Arrays.asList;

/**
 * @Auther: viagra
 * @Date: 2019/11/18 16:09
 * @Description:
 */
public class Lesson2 {

    public static void test(){
        AppleTree[] appleTrees = AppleTree.newTreeGarden(6);

        Callable<Void> applePicker1 = createApplePicker(appleTrees,0,2, "Alex");
        Callable<Void> applePicker2 = createApplePicker(appleTrees, 2, 4, "Bob");
        Callable<Void> applePicker3 = createApplePicker(appleTrees, 4, 6, "Carol");

        ForkJoinPool.commonPool().invokeAll(asList(applePicker1,applePicker2,applePicker3));

        System.out.println();
        System.out.println("All fruits collected!");
    }


    public static void main(String[] args) {

        test();
    }
    public static Callable<Void> createApplePicker(AppleTree[] appleTrees, int fromIndexInclusive, int toIndexExclusive,
                                                   String workerName) {
        return ()->{
            for (int i = fromIndexInclusive; i < toIndexExclusive; i++){
                appleTrees[i].pickApples(workerName);
            }
            return null;
        };

    }
}
