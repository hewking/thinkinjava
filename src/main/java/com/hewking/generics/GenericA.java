package com.hewking.generics;

public class GenericA<T> {

    public T t;

    public static void main(String[] args) {
        GenericA.GeneericB generic = new GenericA.GeneericB<String, Integer>();
        generic.b = 333;
        generic.t = "hahada";
        System.out.println("B : " + generic.b + "A : " + generic.t);
    }

    public static class GeneericB<B, A> extends GenericA<A> {

        public B b;


    }

}


