package com.davidbonelo.models;

import net.datafaker.Faker;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Class {
    private final List<Student> students;
    private int id;
    private String name;
    private ClassStatus status = ClassStatus.DUE;

    public Class(int id, String name) {
        this.id = id;
        this.name = name;
        this.students = new ArrayList<>();
    }

    public static Class createFakeClass(Faker faker) {
        faker = faker == null ? new Faker() : faker; // new faker if didn't receive one
        String courseName = faker.educator().subjectWithNumber();
        courseName = courseName.substring(0, courseName.length() - 4);
        return new Class(faker.idNumber().hashCode(), courseName);
    }

    public static List<Class> createFakeClasses(int amount) {
        Faker faker = new Faker(new Locale("es"));
        List<Class> classes = new ArrayList<>();
        for (int i = 0; i < amount; i++) {
            classes.add(Class.createFakeClass(faker));
        }
        return classes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ClassStatus getStatus() {
        return status;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void startClass() {
        if (this.status == ClassStatus.DUE) {
            this.status = ClassStatus.IN_PROGRESS;
        } else {
            System.out.println("Couldn't start this class because current status id: " + this.status);
        }
    }

    public void endClass() {
        if (this.status == ClassStatus.IN_PROGRESS) {
            this.status = ClassStatus.ENDED;
        } else {
            System.out.println("Couldn't end this class because current status is:" + this.status);
        }
    }

    public void addStudent(Student student) {
        boolean alreadyInThisClass = searchStudent(student.getFullName()) != null;
        if (alreadyInThisClass) {
            System.out.println("The student: " + student.getFullName() + " is already attending " + "this clas");
        } else {
            students.add(student);
        }
    }

    public void removeStudent(Student student) {
        int foundIdx = this.students.indexOf(student);
        if (foundIdx == -1) return;
        this.students.remove(foundIdx);
    }

    public Student searchStudent(String fullName) {
        for (Student student : this.students) {
            if (student.getFullName().equals(fullName)) {
                return student;
            }
        }
        return null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void printStudents() {
        System.out.println("____________________________________________");
        System.out.println("Students attending class " + name + ": ");
        getStudents().forEach(System.out::println);
        System.out.println("____________________________________________");
    }
}
