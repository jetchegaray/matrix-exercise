package com.league.matrix.request.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.league.matrix.domain.Matrix;

public class MatrixTransformerTest {

    private MatrixTransformer transformer;

    @Before
    public void setUp() {
        transformer = new MatrixTransformer();
    }


    @Test
    public void csvToMatrixTransform() throws IOException {

        String line = "1,2,3\n4,5,6\n7,8,9\n";
        Integer[][] rightMatrix = { {1,2, 3}, {4,5,6}, {7,8,9}};

        Matrix result = transformer.csvToMatrixTransform(new BufferedReader(new StringReader(line)));

        for (int i = 0; i < result.getRows(); i++) {
            for (int j = 0; j < result.getColumns(); j++) {
                Assert.assertEquals(result.getData()[i][j],rightMatrix[i][j]);
            }
        }
    }
}