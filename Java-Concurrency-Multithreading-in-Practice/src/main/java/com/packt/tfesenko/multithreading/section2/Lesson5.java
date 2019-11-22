package com.packt.tfesenko.multithreading.section2;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;
/**
 * @Auther: viagra
 * @Date: 2019/11/18 19:58
 * @Description:
 */
public class Lesson5 {


    public static void main(String[] args) {
        Stream.of("One", "Two", "Three")
                // to parallel stream
                .parallel();

        // Or, create directly from a list
        Arrays.asList("January", "February", "March")
                // parallelStream() instead of stream()
                .parallelStream();

        // array - parallel() from a stream
        String[] array = {"Coffee", "Capuccino", "Latte"};
        Arrays.stream(array).parallel();

        // parallel reduction using/producing a concurrent map
        ConcurrentMap<Object, List<String>> coffeeByFirstLetter =
                Arrays.stream(array).parallel()//
                        .collect(Collectors.groupingByConcurrent(drink->drink.charAt(0)));
        System.out.println(coffeeByFirstLetter);

    }
}
