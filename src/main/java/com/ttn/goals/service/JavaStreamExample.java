package com.ttn.goals.service;

import ch.qos.logback.core.net.SyslogOutputStream;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class JavaStreamExample {

    public void streamPractice() {


        List<Integer> number = Arrays.asList(2, 3, 4, 5);

        //Square of number
        List<Integer> list1 = number.stream().map(x -> x * x).collect(Collectors.toList());

        //To find out Even Number
        System.out.println("Hey" + list1.stream().filter(o -> o % 2 == 0).collect(Collectors.toList()));


        List<Integer> list = new ArrayList<Integer>();

        for (int i = 1; i < 11; i++) {
            list.add(i);
        }

        List<String> stringList = new ArrayList<String>(Arrays.asList("Prakhar", "Sharma", "TTN", "Noida"));


        System.out.println(stringList.stream().anyMatch(s -> s.startsWith("p")));

        stringList.stream().filter(s -> s.startsWith("I")).forEach(System.out::println);


        List<String> list2 = new ArrayList<>();
        list2.add("Amit");
        list2.add("Shan");
        list2.add("Aman");
        list2.add("Rahul");
        list2.add("Sonu");
        list2.add("Sonam");
        list2.add("Zoya");
        list2.add("Raj");

        list2.stream().filter(s -> s.startsWith("S")).forEach(System.out::println);
        list2.stream().sorted().forEach(System.out::println);
        System.out.println();
        list2.stream().sorted(Comparator.reverseOrder()).forEach(System.out::println);

        list2.stream().filter(s -> s.startsWith("A")).map(String::toUpperCase).forEach(System.out::println);
        System.out.println(list2.stream().filter(s -> s.startsWith("A")).map(String::toUpperCase).reduce((s, s2) ->s +" "+s2 ).get());
        System.out.println("Count :: "+stringList.stream().filter(s -> s.startsWith("A")).count());

        List<String> sList = new ArrayList<String>();

        sList.add("abc");
        sList.add("def");
        sList.add("xysf");


        Stream<String> stream = sList.stream();

        Optional<String> max = stream.max((val1, val2) -> {
            return val1.compareTo(val2);
        });
        String maxString = max.get();
        System.out.println(maxString);






    }


}
