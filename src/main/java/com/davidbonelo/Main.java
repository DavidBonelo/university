package com.davidbonelo;

import com.davidbonelo.models.Class;
import com.davidbonelo.models.Student;
import com.davidbonelo.models.University;

import java.util.List;


public class Main {
    public static void main(String[] args) {
        University university = new University("Software Kaizen University");
        runDemo(university);
    }

    private static void runDemo(University university) {
        List<Class> clases = Class.createFakeClasses(10);
        university.setClasses(clases);
        List<Student> students = Student.createFakeStudents(30);

        // Every student is attending a class
        System.out.println("Students starting to enter to their classes");
        for (Student student : students) {
            university.attendClass(student, getRandomItem(clases));
        }

        var randomClass = getRandomItem(clases);
        var anotherRandomClass = getRandomItem(clases);
        var randomStudent = getRandomItem(randomClass.getStudents());

        randomClass.printStudents();

        // Trying to attend 2 classes
        university.attendClass(randomStudent, anotherRandomClass);

        System.out.println("Removing student " + randomStudent.getFullName() + " from class " + randomClass.getName());
        randomClass.removeStudent(randomStudent);

        randomClass.printStudents();

        university.attendClass(randomStudent, anotherRandomClass);
        anotherRandomClass.printStudents();
    }


    private static <T> T getRandomItem(List<T> list) {
        int randomIdx = (int) (Math.random() * list.size());
        return list.get(randomIdx);
    }

    private Student getRandomStudentInClass(University university) {
        var clases = university.getInProgressClasses();
        if (clases.size() == 0) return null;
        for (int i = 0; i < clases.size(); i++) {
            var students = getRandomItem(clases).getStudents();
            if (students.size() != 0) {
                return getRandomItem(students);
            }
        }
        return null;
    }
}