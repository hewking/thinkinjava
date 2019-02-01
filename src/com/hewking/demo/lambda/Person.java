package com.hewking.demo.lambda;

public class Person {

    public Person gen() {
        return null;
    }

    public enum Sex{
        MALE,FEMALE
    }

    private String name;
    private int age;
    private Sex gendar;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Sex getGendar() {
        return gendar;
    }

    public void setGendar(Sex gendar) {
        this.gendar = gendar;
    }

    interface opField{
        void opAge(Person age);
    }

    interface OpConstructor{
        void opC(Person p);
    }

    public void op(opField op){

    }

    public void opC(OpConstructor constructor){

    }

    public void p(Person person) {

    }

    public void gen(opGen gen){

    }

    interface opGen{
        Person gen();
    }



    public static void main(String[] args) {
        Person p = new Person();
        p.opC(Person::getAge);
        p.opC(p2-> p2.getAge());

        p.op(Person::getName);
        p.op(p3 -> p3.getName());
        p.op(p3 -> {
            p3.getName();
            p3.getAge();
        });

        p.gen(Person::new);
    }

    public void lambda(){
        Person p = new Person();
        p.opC(Person::getAge);
    }
}
