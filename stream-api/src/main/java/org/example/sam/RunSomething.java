package org.example.sam;

@FunctionalInterface // java standard lib에 들어서 별도 import가 되지 않음
public interface RunSomething {

    // 함수형 인터페이스
    void doIt(); // interface는 abstract 생략가능

    // <자바8의 추가 기능>
    // 1. static 메서드
    static void printName() {
        System.out.println("jisu");
    }

    // 2. default 메서드
    default void printAge() {
        System.out.println("27");
    }

}