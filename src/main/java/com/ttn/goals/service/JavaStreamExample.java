package com.ttn.goals.service;

import ch.qos.logback.core.net.SyslogOutputStream;

import java.util.ArrayList;
import java.util.Arrays;
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

        List<String> s1 =stringList.stream().filter(s -> s.startsWith("p")).collect(Collectors.toList());
        System.out.println(s1);



   /* Stream<Integer> stream1 = list.stream();
    stream1.forEach(p -> System.out.print(p));
    System.out.println();*/


    }


}
