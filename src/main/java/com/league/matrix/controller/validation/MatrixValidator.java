package com.league.matrix.controller.validation;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.league.matrix.domain.Matrix;
import com.league.matrix.request.utils.MatrixTransformer;

/**
 * validate all the stuffs to make sure that a matrix will be valid
 *
 * it could be implemented an interface .
 */
@Component
public class MatrixValidator {

	private static final Logger logger = LoggerFactory.getLogger(MatrixValidator.class);

	public Boolean isValid(Matrix matrix){
		return !(matrix.getRows() <= 1 || matrix.getColumns() <= 1 || matrix.getRows() != matrix.getColumns());
	}

}
