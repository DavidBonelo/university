package com.davidbonelo.models;

import java.util.ArrayList;
import java.util.List;

public class University {
    private List<Class> classes;

    public University(String name) {
        this.classes = new ArrayList<>();
    }

    public void addClass(Class newClass) {
        classes.add(newClass);
    }

    public List<Class> getClasses() {
        return classes;
    }

    public void setClasses(List<Class> classes) {
        this.classes = classes;
    }

    public List<Class> getInProgressClasses() {
        return classes.stream().filter(c -> c.getStatus() == ClassStatus.IN_PROGRESS).toList();
    }

    /**
     * @param fullName the names and the last names separated by a space
     * @return the class where the student was found, null if not found.
     */
    public Class searchStudent(String fullName) {
        for (Class c : this.classes) {
            Student foundStudent = c.searchStudent(fullName);
            if (foundStudent != null) {
                return c;
            }
        }
        return null;
    }

    public void attendClass(Student student, Class c) {
        if (searchStudent(student.getFullName()) != null) {
            System.out.println("The student is already attending another class");
        } else {
            c.addStudent(student);
            System.out.println("Student: " + student.getFullName() + " is now attending to " + c.getName() + " class.");
        }
    }
}
