package com.bm;

class Address{
    String city;

    Address(String city){
        this.city=city;
    }
}

class Person implements Cloneable{
    String name;
    Address address;

    Person(String name, Address address){
        this.name=name;
        this.address=address;
    }

    protected Object clone() throws CloneNotSupportedException{
        return super.clone();
    }
}
public class ShallowCopy {
    public static void main(String[] args) throws CloneNotSupportedException{
        Address address = new Address("Pune");
        Person person = new Person("ABC",address);
        Person person1 = (Person) person.clone(); //shallow copy
        System.out.println("Original city: "+person.address.city);
        System.out.println("Copied city: "+person1.address.city);
        person1.address.city="Mumbai";
        System.out.println("After change:");
        System.out.println("Original City: " + person.address.city);
        System.out.println("Copied City: " + person1.address.city);
    }
}
