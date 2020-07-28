package com.ling;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author TianHeLing
 * @Description
 * @date 2020/3/13
 */
public class TestMain {

    public static void main(String[] args) {
        List<String> letters = Arrays.asList("A", "B", "C", "D");
        // String collect = letters.stream().collect(Collectors.joining()); //ABCD
        String collect = letters.stream().collect(Collectors.joining(",")); //A,B,C,D,,,
        System.out.println(collect);

        String join = String.join(",", letters); //A,B,C,D
        System.out.println(join);
    }
}
