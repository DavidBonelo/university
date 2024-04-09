package com.davidbonelo.models;

import net.datafaker.Faker;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;

public class Student {
    private int id;
    private String name;
    private String lastName;

    public Student(int id, String name, String lastName) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
    }

    public static Student createFakeStudent(Faker faker) {
        faker = faker == null ? new Faker() : faker; // new faker if didn't receive one
        return new Student(faker.idNumber().hashCode(), faker.name().firstName(),
                faker.name().lastName());
    }

    public static List<Student> createFakeStudents(int amount) {
        Faker faker = new Faker(new Locale("es"));
        List<Student> students = new ArrayList<>();
        for (int i = 0; i < amount; i++) {
            students.add(Student.createFakeStudent(faker));
        }
        return students;
    }

    public static void printStudentsList(Collection<Student> students) {
        System.out.println("____________________________________________");
        students.forEach(System.out::println);
        System.out.println("____________________________________________");
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFullName() {
        return name + " " + lastName;
    }

    @Override
    public String toString() {
        return "Student{" + "id=" + id + ", name='" + name + '\'' + ", lastName='" + lastName + '\'' + '}';
    }
}
