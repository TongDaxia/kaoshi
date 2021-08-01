package com.kaoshi.tyg.common.实践;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamFlatMapTest {

    private static List<Student> computerClub = Arrays.asList(
            new Student("小明", 15),
            new Student("小王", 14),
            new Student("小张", 15),
            new Student("小梁", 17)
    );
    private static List<Student> basketballClub = Arrays.asList(
            new Student("小c", 13),
            new Student("小s", 14),
            new Student("小d", 15),
            new Student("小y", 16)
    );
    private static List<Student> pingpongClub = Arrays.asList(
            new Student("小u", 16),
            new Student("小i", 14),
            new Student("小m", 17),
            new Student("小n", 16)
    );

    private static List<List<Student>> allClubStu = new ArrayList<>();

    public static void main(String[] args) {

        allClubStu.add(computerClub);
        allClubStu.add(basketballClub);
        allClubStu.add(pingpongClub);

        //map 一对一 映射处理
        computerClub.stream().map(student -> {
            return new StudentDTO(student.getName(), student.getAge());
        }).collect(Collectors.toList()).forEach(System.out::println);

        System.out.println("--------------------------");


        //flatMap  一对多映射处理，深入到多个stream内部去处理子元素，统一输出
        List<Student> studentList = allClubStu.stream().flatMap(
                e -> e.stream().filter(student -> student.getAge() > 15)
        ).collect(Collectors.toList());
        studentList.forEach(System.out::println);

        System.out.println("=========================");

        List<Stream<Student>> collect = allClubStu.stream().map(
                e -> e.stream().filter(student -> student.getAge() > 15)
        ).collect(Collectors.toList());
        collect.forEach(System.out::println);

    }


    static class Student {


        private String name;
        private Integer age;

        public Student(String name, Integer age) {

            this.name = name;
            this.age = age;

        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getAge() {
            return age;
        }

        public void setAge(Integer age) {
            this.age = age;
        }

        @Override
        public String toString() {
            return "Student{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    '}';
        }
    }

    static class StudentDTO {


        private String name;
        private Integer age;

        public StudentDTO(String name, Integer age) {

            this.name = name;
            this.age = age;

        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getAge() {
            return age;
        }

        public void setAge(Integer age) {
            this.age = age;
        }

        @Override
        public String toString() {
            return "StudentDTO{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    '}';
        }
    }
}
