package com.mangkyu.basic;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * BasicTest.java
 * Class 설명을 작성하세요.
 *
 * @author danusys
 * @since 2023.09.21
 */
public class BasicTest {

    /**
     * Stream 변환 방법
     */
    @Test
    public void collectionTest() {
        //Collection 의 Stream 생성
        List<String> list = Arrays.asList("a", "b", "c", "d");
        Stream<String> stream = list.stream();

        //Array의 Stream 생성
        Stream.of("a", "b", "c");
        Stream.of(new String[] {"a", "b", "c"});
        Arrays.stream(new String[] {"a", "b", "c"});
        Arrays.stream(new String[] {"a", "b", "c"}, 0, 3); // end 범위 포함X

        //원시 Stream 생성
        IntStream intStream = IntStream.range(4, 10);

    }

    /**
     * 필터
     */
    @Test
    public void filterTest() {
        List<String> list = Arrays.asList("apple","pineapple", "melon", "orange", "grape", "banana", "coffe");
        list.stream().filter(name -> name.contains("a")).forEach(System.out::println);
    }

    /**
     * 형태 변경
     */
    @Test
    public void mapTest() {
        List<String> list = Arrays.asList("apple","pineapple", "melon", "orange", "grape", "banana", "coffe");
        list.stream().map(name -> name.toUpperCase()).forEach(System.out::println);

        System.out.println();

        Stream<File> fileStream = Stream.of(new File("Test.java"), new File("Test1.java"), new File("Test2.java"));
        fileStream.map(File::getName).forEach(System.out::println);
    }

    /**
     * 정렬
     */
    @Test
    public void sortedTest() {
        List<String> list = Arrays.asList("Java", "Scala", "Groovy", "Python", "Go", "Swift");

        list.stream().sorted().forEach(System.out::println);

        System.out.println();

        list.stream().sorted(Comparator.reverseOrder()).forEach(System.out::println);
    }

    /**
     * 중복 제거
     */
    @Test
    public void distinctTest() {
        List<String> list = Arrays.asList("Java", "Scala", "Groovy", "Python", "Go", "Swift", "Java");
        list.stream().distinct().forEach(System.out::println);

        // 서로다른 객체의 중복을 판단하기 위해서는 객체에 equals, hashCode를 오버라이드 해야만 한다.
        Employee e1 = new Employee("yong");
        Employee e2 = new Employee("yong");
        Employee e3 = new Employee("wook");

        List<Employee> employees = new ArrayList<>();
        employees.add(e1);
        employees.add(e2);
        employees.add(e3);

        int size = employees.stream().distinct().collect(Collectors.toList()).size();
        System.out.println(size);
    }

    public class Employee {
        private String name;

        public Employee(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Employee employee = (Employee) o;
            return Objects.equals(name, employee.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name);
        }
    }

    /**
     * Stream에 영향을 주지 않고 특정 연산 수행`
     * 예를들어 stream의 요소들을 중간에 출력하기를 원할 때 사용
     */
    @Test
    public void peekTest() {
        int sum = IntStream.of(1, 3, 5, 7, 9)
                .peek(System.out::println)
                .sum();
    }

    @Test
    public void primitiveTest() {
        // IntStream -> Stream<String>
        IntStream.range(1,4)
                .mapToObj(i -> "a" + i)
                .forEach(System.out::println);

        System.out.println();

        // Stream<Double> -> IntStream -> Stream<String>
        Stream.of(1.0, 2.0, 3.0)
                .mapToInt(Double::intValue)
                .mapToObj(i ->"a" +i)
                .forEach(System.out::println);

        // mapToInt(), mapToLong(), mapToDouble()
        // mapToObj()
    }


}
