package org.example.sam;

import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class Main {
    public static void main(String[] args) {

        /** 익명 내부 클래스 anonymous inner class */

        // 자바8 이전
        RunSomething runSomething1 = new RunSomething() {
            @Override
            public void doIt() {
                System.out.println("Hello");
            }
        };

        // 자바 8
        RunSomething runSomething2 = () -> System.out.println("Hello");

        RunSomething runSomething3 = () -> {
            System.out.println("Hello");
            System.out.println("Bye");
        };

        runSomething1.doIt();
        runSomething2.doIt();
        runSomething3.doIt();

        Supplier<String> supplier = () -> "Hello World!";
        System.out.println(supplier.get());

        // 예시
        Consumer<String> consumer = (str) -> System.out.println(str.split(" ")[0]);
        consumer.andThen(System.out::println).accept("Hello World");

        // 예시, 메소드 참조로 간소화 가능(String::length;)
        Function<String, Integer> function = str -> str.length();
        function.apply("Hello World");

        // 예시
        Predicate<String> predicate = (str) -> str.equals("Hello World");
        System.out.println(" = " + predicate.test("Hello World"));
        Predicate<Boolean> predicate2 = Objects::isNull;
        System.out.println(predicate2.test(null));
    }
}