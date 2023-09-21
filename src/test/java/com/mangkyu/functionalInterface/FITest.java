package com.mangkyu.functionalInterface;

import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.function.*;

/**
 * FITest.java
 * Class 설명을 작성하세요.
 *
 * @author danusys
 * @since 2023.09.21
 */
public class FITest {

    @Test
    public void supplierTest() {
        // 매개변수가 없고 반환 타입이 String
        Supplier<String> sup1 = () -> "Hello";

        System.out.println(sup1.get());
    }

    @Test
    public void consumerTest() {
        // 매개변수로 String 타입을 받지만 Return이 없다.
        Consumer<String> consumer1 = s -> System.out.println(s+"앤드");
        Consumer<String> consumer2 = s -> System.out.println(s);

        consumer1.accept("Hello");
        consumer2.andThen(consumer1).accept("Hello");
    }

    @Test
    public void functionTest() {
        // String 타입의 인자를 받아 Integer 타입을 반환
        Function<String, Integer> func1 = (s) -> s.equals("hello") ? 1: 0;

        System.out.println(func1.apply("hello"));

        Function<Integer, Integer> func2 = (i) -> i+1;
        Function<String, String> func3 = (s) -> s.equals("a") ? "hello":"not";

        // func1에 hello를 넣고 나온 값을 func2에 넣어서 값 출력
        System.out.println(func2.compose(func1).apply("hello"));

        //func3에 hello를 넣고 나온 값을 func1에 넣어서 값 출력
        System.out.println(func3.andThen(func1).apply("hello"));

        //func3에 w를 넣고 나온 값을 func1에 넣고 그 값을 func2에 넣은 값을 출력
        System.out.println(func1.andThen(func2).compose(func3).apply("w"));
    }

    @Test
    public void predicateTest() {
        // String 타입을 받아 boolean 타입을 반환한다.
        Predicate<String> predicate1 = s -> s.equals("test");
        Predicate<String> predicate2 = s -> s.equals("not");

        // predicate1 or predicate2
        System.out.println(predicate1.or(predicate2).test("test"));
        // predicate1 and predicate2
        System.out.println(predicate1.and(predicate2).test("test"));
        // !predicate1
        System.out.println(predicate1.negate().test("test"));

    }

    @Test
    public void operatorTest() {
        // Integer를 받아 연산 후 Integer를 반환
        UnaryOperator<Integer> operator = i -> i * i;
        // 받은 인자를 그대로 반환
        UnaryOperator<Integer> operator2 = UnaryOperator.identity();

        System.out.println(operator.apply(2));
        System.out.println(operator2.apply(3));

        // Integer 인자 두개 받아 연산 후 Integer 반환
        BinaryOperator<Integer> biOperator = (i, j) -> i * j;

        System.out.println(biOperator.apply(2,3));

        Comparator<Integer> comparator = (a, b) -> a.compareTo(b);
        // Comparator을 받아 해당 비교를 연산해 값을 반환해준다.
        BinaryOperator<Integer> biOperator2 = BinaryOperator.minBy(comparator);
        BinaryOperator<Integer> biOperator3 = BinaryOperator.maxBy(comparator);

        System.out.println(biOperator2.apply(1,2));
        System.out.println(biOperator3.apply(1,2));


    }
}
