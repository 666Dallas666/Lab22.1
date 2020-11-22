package com.company;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
class Student{
    private int ID;
    private double GPA = -1;
    private String name;
    void setGPA(double GPA){
        this.GPA = GPA;
    }
    void setName(String name){
        this.name = name;
    }
    void setID(int ID){
        this.ID = ID;
    }
    int getID(){
        return ID;
    }
    double getGPA(){
        return GPA;
    }
    String getName(){
        return name;
    }
    Student(String name, double GPA, int ID){
        setID(ID);
        setName(name);
        setGPA(GPA);
    }
    @Override
    public String toString() {
        return "Student{" +
                "ID=" + ID +
                ", GPA=" + GPA +
                ", name='" + name + '\'' +
                '}';
    }
}
class SortingStudentsByGPA implements Comparator<Student>{
    @Override
    public int compare(Student student1, Student student2) {
        if (student1.getGPA() < student2.getGPA())
            return 1;
        if (student2.getGPA() < student1.getGPA())
            return -1;
        return 0;
    }
}
class User {
    String name;
    String INN;
    public User(String name, String INN) {
        this.name = name;
        this.INN = INN;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getINN() {
        return INN;
    }
    public void setINN(String INN) {
        this.INN = INN;
    }
}
class Shop {
    void Market(){
        ArrayList<User> userList = new ArrayList();
        Scanner scanner = new Scanner(System.in);
        boolean flag = true;
        boolean check = true;
        while(check) {
            System.out.println("Введите имя и ИНН(12 символов):");
            String name = scanner.next();
            String INN = scanner.next();
            if (INN.length() != 12) {
                System.out.println("Введен неверный ИНН");
                continue;
            } else {
                for (int j = 0; j < userList.size(); j++)
                    if (userList.get(j).INN.equals(INN)) {
                        System.out.println("Ваш ИНН был найден в базе");
                        flag = false;
                        check = false;
                        break;
                    }
                if (flag) {
                    userList.add(new User(name, INN));
                    System.out.println("Пользователь добавлен");
                    check = false;
                }
                flag = true;
            }
        }
    }
}
public class Main {
    public static int searchStudent(ArrayList<Student> array, String name) throws NoSuchFieldException {
        for (int i = 0; i < array.size(); i++){
            if (array.get(i).getName().equals(name)){
                return i;
            }
        }
        throw new NoSuchFieldException();
    }
    public static void main(String[] args) throws NoSuchFieldException {
        ArrayList<Student> students = new ArrayList();
        Student student1 = new Student("Alex",2.8,5);
        Student student2 = new Student("Max",3.6,4);
        Student student3 = new Student("Sasha",4.3,1);
        Student student4 = new Student("Jeff",5,2);
        Student student5 = new Student("Jack",3.1,3);
        students.add(student1);
        students.add(student2);
        students.add(student3);
        students.add(student4);
        students.add(student5);
        ArrayList IDNum = new ArrayList();
        students.forEach(student -> IDNum.add(student.getID()));
        Collections.sort(IDNum);
        System.out.println(IDNum);
        Collections.sort(students, new SortingStudentsByGPA());
        students.forEach(student -> System.out.println(student));
        System.out.println("\nИщем студента Jeff");
        try{
            System.out.println(students.get(searchStudent(students, "Jeff")));
        }
        catch (NoSuchFieldException ex){
            System.out.println("Ошибка: " + ex);
        }
        System.out.println("\nИщем студента Ivan");
        try{
            System.out.println(students.get(searchStudent(students, "Ivan")));
        }
        catch (NoSuchFieldException ex){
            System.out.println("Ошибка: " + ex);
        }
        Shop Test = new Shop();
        Test.Market();
        Test.Market();
        Test.Market();
    }
}