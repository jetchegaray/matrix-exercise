package com.league.matrix.domain;

import java.util.Arrays;
import java.util.stream.Collectors;

import org.assertj.core.util.VisibleForTesting;

/*
 * Java class to represent a Matrix. It uses a two dimensional array to
 * represent a Matrix.
 */
public class Matrix {

    private Integer rows;
    private Integer columns;
    private Integer[][] data;

    public Matrix(Integer row, Integer column) {
        this.rows = row;
        this.columns = column;
        data = new Integer[rows][columns];
    }

    public Matrix(Integer[][] data) {
        this.data = data;
        this.rows = data.length;
        this.columns = Integer.MAX_VALUE;

        for (int i = 0; i < data.length; i++) {
            this.columns = Integer.min(this.columns, data[i].length);
        }
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

    @VisibleForTesting
    public Integer[][] getData() {
        return data;
    }

    /**
     * This method will transpose this matrix
     *
     * @return this object to continue executing operations (builder design pattern)
     */
    public Matrix transpose() {
        Integer[][] temp = new Integer[columns][rows];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                temp[j][i] = data[i][j];
            }
        }
        data = temp;
        return this;
    }


    /**
     * Java8 convert the whole matrix in a string with the form an representation of  a matrix. plan text
     *
     *
     * @return String of the matrix as it is
     */
    @Override
    public String toString() {
        StringBuilder line = new StringBuilder();
        for (int i = 0; i < this.getColumns(); i++) {
            for (int j = 0; j < this.getRows(); j++) {
                line.append(String.valueOf(data[i][j])).append(",");
            }
            line.deleteCharAt( line.length()-1).append("\n");
        }
        return line.toString();
    }

    /**
     * Java8 each row is converted  to an int. flattened those integers like a list of integers and added them up.
     *
     * @return the sum of all elements of a matrix
     */

    public Integer sum() {
        return Arrays.stream(data).flatMapToInt(row -> Arrays.stream(row).mapToInt(Integer::new)).sum();
    }


    /**
     * Java8 reduce each row and then each column  multiplying all the elements inside
     *
     * @return the multiplication of all elements of a matrix
     */
    public Long multiply() {
        return Long.valueOf(Arrays.stream(data).mapToInt(row -> Arrays.stream(row).reduce(1, Math::multiplyExact)).reduce(1, Math::multiplyExact));
    }


    /**
     * Java8 flatten all the elements with a joinned by a comma
     *
     * @return a string of all elements separate them by commas
     */
    public String flatten() {
        return Arrays.stream(data).map(row -> Arrays.stream(row).map(String::valueOf).collect(Collectors.joining(",")))
                .collect(Collectors.joining(","));
    }
}
