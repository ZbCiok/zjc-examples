package com.jreact.java8streams.streams.collectors.reducing;

import org.junit.jupiter.api.Test;

public class ReducingTests {

    Reducing reducing = new Reducing();

    @Test
    public void reducingOp01() {
        reducing.reducingOp01();
    }

    @Test
    public void reducingOp02() {
        reducing.reducingOp02();
    }

    @Test
    public void reducingOpId01() {
        reducing.reducingOpId01();
    }

    @Test
    public void reducingOpId02() {
        reducing.reducingOpId02();
    }

    @Test
    public void reducingOpIdFun01() {
        reducing.reducingOpIdFun01();
    }

    @Test
    public void reducingOpIdFun02() {
        reducing.reducingOpIdFun02();
    }
}
