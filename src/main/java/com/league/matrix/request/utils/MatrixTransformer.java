package com.league.matrix.request.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.assertj.core.util.Lists;
import org.springframework.stereotype.Component;

import com.league.matrix.domain.Matrix;

@Component
public class MatrixTransformer {

    /**
     * I could this using java8 tools and data structure. and do it in three simple lines .
     * but I think the idea of the exercise wasn't that. so I've used a mix between old java code.
     * and lambda expressions
     *
     * @param buffer
     * @return matrix object represented a matrix
     */

    public Matrix csvToMatrixTransform(BufferedReader buffer) throws IOException {

        List<String[]> matrixAsLists = Lists.newArrayList();
        String line;

        while ((line = buffer.readLine()) != null) {
            matrixAsLists.add(line.split(","));
        }

        Integer[][] matrix = new Integer[matrixAsLists.size()][];
        for (int i = 0; i < matrixAsLists.size(); i++) {
            matrix[i] = Arrays.stream(matrixAsLists.get(i)).map(cell -> Integer.valueOf(cell)).toArray(Integer[]::new);
        }

        return new Matrix(matrix);
    }


}
