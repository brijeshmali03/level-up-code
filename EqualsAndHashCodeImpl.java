package com.bm;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class EqualsAndHashCodeImpl {
    private final int id;
    private final String name;
    private final String department;

    public EqualsAndHashCodeImpl(int id, String name, String department) {
        this.id = id;
        this.name = name;
        this.department = department;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDepartment() {
        return department;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        EqualsAndHashCodeImpl that = (EqualsAndHashCodeImpl) o;

        return id == that.id &&
               Objects.equals(name, that.name) &&
               Objects.equals(department, that.department);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, department);
    }

    @Override
    public String toString() {
        return "EqualsAndHashCodeImpl{id=" + id + ", name='" + name + "', department='" + department + "'}";
    }

    public static void main(String[] args) {
        EqualsAndHashCodeImpl obj1 = new EqualsAndHashCodeImpl(1, "Alice", "Engineering");
        EqualsAndHashCodeImpl obj2 = new EqualsAndHashCodeImpl(1, "Alice", "Engineering");
        EqualsAndHashCodeImpl obj3 = new EqualsAndHashCodeImpl(2, "Bob", "Marketing");

        System.out.println("obj1.equals(obj2): " + obj1.equals(obj2)); 
        System.out.println("obj1.equals(obj3): " + obj1.equals(obj3));

        System.out.println("obj1 hashCode: " + obj1.hashCode());
        System.out.println("obj2 hashCode: " + obj2.hashCode());

        Set<EqualsAndHashCodeImpl> set = new HashSet<>();
        set.add(obj1);
        set.add(obj2);
        set.add(obj3);

        System.out.println("Set size: " + set.size());
    }
}