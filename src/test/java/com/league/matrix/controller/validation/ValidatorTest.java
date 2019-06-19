package com.league.matrix.controller.validation;

import javax.xml.validation.Validator;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.league.matrix.domain.Matrix;

public class ValidatorTest {

    private MatrixValidator validator;

    @Before
    public void setUp() {
        validator = new MatrixValidator();
    }

    @Test
    public void validate_2x2_testOK() {

        Integer[][] data = { {1,2}, {3,4}};
        Matrix matrix = new Matrix(data);

        Assert.assertTrue(validator.isValid(matrix));
    }


    @Test
    public void validate_3x3_testOK() {

        Integer[][] data = { {1,2, 3}, {4,5,6}, {7,8,9}};
        Matrix matrix = new Matrix(data);

        Assert.assertTrue(validator.isValid(matrix));
    }

    @Test
    public void validate_1x1_testKO() {

        Integer[][] data = { {1}, {2}};
        Matrix matrix = new Matrix(data);

        Assert.assertFalse(validator.isValid(matrix));
    }


    @Test
    public void validate_1x2_testKO() {

        Integer[][] data = { {1}, {2,3}};
        Matrix matrix = new Matrix(data);

        Assert.assertFalse(validator.isValid(matrix));
    }


    @Test
    public void validate_2x1_testKO() {

        Integer[][] data = { {1,2}, {3}};
        Matrix matrix = new Matrix(data);

        Assert.assertFalse(validator.isValid(matrix));
    }


}
