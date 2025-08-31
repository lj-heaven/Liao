package com.example.base.algorithm;

import com.example.base.entity.BookDemo;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * 去重
 */
public class demo7 {

    static <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
        Map<Object,Boolean> seen = new ConcurrentHashMap<>();
        return t -> seen.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }

    public static void main(String[] args) {
        //对包含重复元素的字符串数据类型列表去重
//        List<String> list = Arrays.asList("A", "B", "C", "B", "C", "A", "A");
//        long num = list.stream().distinct().count();
//        System.out.println("No. of distinct elements:" + num);
//        String text = list.stream().distinct().collect(Collectors.joining(","));

        //对对象列表去重，按整个对象去重，但该对象需重写hashCode和equals方法
//        List<BookDemo> list = new ArrayList<>();
//        {
//            list.add(new BookDemo("Java", 300));
//            list.add(new BookDemo("Java", 300));
//            list.add(new BookDemo("DB", 100));
//            list.add(new BookDemo("Spring", 200));
//            list.add(new BookDemo("Spring", 200));
//        }
//        long l = list.stream().distinct().count();
//        System.out.println("No. of distinct books:"+l);
//        list.stream().distinct().forEach(b -> System.out.println(b.getName()+ "," + b.getPrice()));

        //按照属性对对象列表进行去重的直接实现，指定某个字段去重
//        List<BookDemo> list = new ArrayList<>();
//        {
//            list.add(new BookDemo("Java", 200));
//            list.add(new BookDemo("DB", 100));
//            list.add(new BookDemo("Spring", 200));
//            list.add(new BookDemo("Spring", 200));
//        }
//        list.stream().filter(distinctByKey(b -> b.getName()))
//                .forEach(b -> System.out.println(b.getName()+ "," + b.getPrice()));

        //指定根据某个属性去重
        List<BookDemo> list = new ArrayList<>();
        {
            list.add(new BookDemo("Java", 300));
            list.add(new BookDemo("Java", 200));
            list.add(new BookDemo("Java", 200));
            list.add(new BookDemo("DB", 100));
            list.add(new BookDemo("Spring", 200));
            list.add(new BookDemo("Spring", 100));
        }
        //方法一
        TreeSet<BookDemo> books = new TreeSet<>(Comparator.comparing(s -> s.getPrice()));
        for (BookDemo book : list) {
            books.add(book);
        }
        new ArrayList<>(books).forEach(System.out::println);
        //方法二
        list.stream().collect(Collectors.collectingAndThen(Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(s -> s.getPrice()))), ArrayList::new)).forEach(System.out::println);


    }

}
