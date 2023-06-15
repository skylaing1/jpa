package de.gfos.summercamp.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.Objects;

@Entity
public class Mitarbeiter {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String lastname;
    private String firstname;
    private int age;
    private double salary;

    public Mitarbeiter(Long id, String lastname, String firstname, int age, double salary) {
        this.id = id;
        this.lastname = lastname;
        this.firstname = firstname;
        this.age = age;
        this.salary = salary;
    }

    public Mitarbeiter() {

    }
    public Mitarbeiter(String lastname, String firstname, int age, double salary) {
        this.lastname = lastname;
        this.firstname = firstname;
        this.age = age;
        this.salary = salary;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Mitarbeiter{" +
                "id=" + id +
                ", lastname='" + lastname + '\'' +
                ", firstname='" + firstname + '\'' +
                ", age=" + age +
                ", salary=" + salary +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Mitarbeiter mitarbeiter = (Mitarbeiter) o;
        return id != null && Objects.equals(id, mitarbeiter.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

}
