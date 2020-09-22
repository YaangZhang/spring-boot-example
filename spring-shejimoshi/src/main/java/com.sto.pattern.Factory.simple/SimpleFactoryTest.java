package com.sto.pattern.Factory.simple;

/**
 * @author zhy
 * @create 2020-08-20-22:15
 */
public class SimpleFactoryTest {
    public static void main(String[] args) {
        CoureFactory factory = new CoureFactory();
        ICoure java = factory.create("java");
        java.record();

        // ICoure factory2 = factory.create2(SimpleFactoryTest.class);
        // factory2.record();
    }
}
