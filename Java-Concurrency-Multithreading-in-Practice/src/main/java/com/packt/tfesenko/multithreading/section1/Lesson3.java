package com.packt.tfesenko.multithreading.section1;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;
import java.util.stream.IntStream;

/**
 * @Auther: viagra
 * @Date: 2019/11/18 16:51
 * @Description:
 */
public class Lesson3 {


    public static void main(String[] args) {

        AppleTree[] appleTrees = AppleTree.newTreeGarden(12);
        ForkJoinPool pool = ForkJoinPool.commonPool();

        PickFruitTask task = new PickFruitTask(appleTrees, 0, appleTrees.length - 1);
        int result = pool.invoke(task);
        System.out.println();
        System.out.println("Total apples picked: " + result);


    }

    public static class PickFruitTask extends RecursiveTask<Integer> {

        private final AppleTree[] appleTrees;
        private final int startInclusive;
        private final int endInclusive;

        private final int taskThreadshold = 4;

        public PickFruitTask(AppleTree[] array, int startInclusive, int endInclusive) {
            this.appleTrees = array;
            this.startInclusive = startInclusive;
            this.endInclusive = endInclusive;
        }

        @Override
        protected Integer compute() {

            if (endInclusive - startInclusive < taskThreadshold){
                return doCompute();
            }

            int midpoint = startInclusive + (endInclusive - startInclusive) / 2;
            PickFruitTask leftSum = new PickFruitTask(appleTrees, startInclusive, midpoint);
            PickFruitTask rightSum = new PickFruitTask(appleTrees, midpoint + 1, endInclusive);

            rightSum.fork();// computed asynchronously
            return leftSum.compute() + rightSum.join();// computed synchronously: immediately and in the current thread


        }
        protected Integer doCompute() {

            return IntStream.rangeClosed(startInclusive,endInclusive)
                    .map(i -> appleTrees[i].pickApples())
                    .sum();

        }

    }


}
