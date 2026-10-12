package com.bm;

class Student implements Cloneable{
    String studentName;
    Student(String studentName){
        this.studentName=studentName;
    }
    @Override
    protected Object clone() throws CloneNotSupportedException{
        return super.clone();
    }
}

class School implements Cloneable{
    String schoolName;
    Student student;

    School(String schoolName, Student student){
        this.schoolName=schoolName;
        this.student = student;
    }
    @Override
    protected Object clone() throws CloneNotSupportedException{
        School cloned = (School)super.clone();
        cloned.student = (Student) student.clone();
        return cloned;
    }
}

public class DeepCopy {
    public static void main(String[] args) throws CloneNotSupportedException{
        Student a1 = new Student("Raj");
        School p1 = new School("XYZ", a1);
        School p2 = (School)p1.clone();
        System.out.println("Original Name: " + p1.student.studentName);
        System.out.println("Copied Name: " + p2.student.studentName);
        p2.student.studentName = "ABC";
        System.out.println("\nAfter modification:");
        System.out.println("Original Name: " + p1.student.studentName);
        System.out.println("Copied Name: " + p2.student.studentName);
    }
}
