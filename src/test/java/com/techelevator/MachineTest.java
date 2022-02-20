package com.techelevator;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;

public class MachineTest {

    private Machine instance;

    @Before
    public void setup() throws IOException {
        File inputFile = new File("VendingMachine.txt");
        instance = new Machine(inputFile);
    }

    @Test
    public void depositMoney_basic_value(){
        Assert.assertEquals(new BigDecimal("5.00"), instance.depositMoney(new BigDecimal("5")));
    }

    @Test
    public void selectProduct_does_it_work(){
        //How do you test a method that doesn't return a value?
    }



}
