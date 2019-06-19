package com.league.matrix.service.impl;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.league.matrix.domain.Matrix;

public class MatrixTest {

    private Matrix matrix;

    @Before
    public void setUp() {
        Integer[][] data = { {1,2, 3}, {4,5,6}, {7,8,9}};
        matrix = new Matrix(data);
    }

    @Test
    public void sum_testOK() {

        Integer result = matrix.sum();

        Assert.assertEquals(Integer.valueOf(45), result);
    }


    @Test
    public void multiply_testOK() {

        Long result = matrix.multiply();

        Assert.assertEquals(Long.valueOf(362880),result);
    }


    @Test
    public void flatten_testOK() {

        String result = matrix.flatten();

        Assert.assertEquals("1,2,3,4,5,6,7,8,9", result);
    }


    @Test
    public void toString_testOK() {

        String result = matrix.toString();

        Assert.assertEquals( "1,2,3\n4,5,6\n7,8,9\n", result);
    }

}
