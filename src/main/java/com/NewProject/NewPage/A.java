package com.NewProject.NewPage;

public class A {

    public static void main(String[] args) {
        new A().test().example();  // Object address with method name then with another method
    }

    public A test()
    {
        return new A();
//        System.out.println("new");
    }
    public  void example()
    {
        System.out.println("new example");
    }
}
