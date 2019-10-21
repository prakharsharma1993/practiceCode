package com.ttn.goals.service;

import ch.qos.logback.core.net.SyslogOutputStream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
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

        List<String> stringList = new ArrayList<String>(Arrays.asList("Prakhar", "Sharma", "Increment", "2019"));


        System.out.println(stringList.stream().anyMatch(s -> s.startsWith("p")));

        stringList.stream().filter(s -> s.startsWith("I")).forEach(System.out::println);


        List<String> list2 = new ArrayList<>();
        list2.add("Amitabh");
        list2.add("Shekhar");
        list2.add("Aman");
        list2.add("Rahul");
        list2.add("Shahrukh");
        list2.add("Salman");
        list2.add("Yana");
        list2.add("Lokesh");

        list2.stream().filter(s -> s.startsWith("S")).forEach(System.out::println);
        list2.stream().sorted().forEach(System.out::println);
        System.out.println();
        list2.stream().sorted(Comparator.reverseOrder()).forEach(System.out::println);

        list2.stream().filter(s -> s.startsWith("A")).map(String::toUpperCase).forEach(System.out::println);
        System.out.println(stringList.stream().filter(s -> s.startsWith("A")).map(String::toUpperCase).reduce((s, s2) ->s +" "+s2 ).get());
        System.out.println("Count :: "+stringList.stream().filter(s -> s.startsWith("A")).count());



   /* Stream<Integer> stream1 = list.stream();
    stream1.forEach(p -> System.out.print(p));
    System.out.println();*/


    }


}
