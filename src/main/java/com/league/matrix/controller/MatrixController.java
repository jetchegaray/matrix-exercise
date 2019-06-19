package com.league.matrix.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.league.matrix.controller.validation.MatrixValidator;
import com.league.matrix.domain.Matrix;
import com.league.matrix.request.utils.MatrixTransformer;

@RestController
@RequestMapping(value = "/")
public class MatrixController {

	private static final Logger logger = LoggerFactory.getLogger(MatrixController.class);

	@Autowired
	private MatrixTransformer transformer;
	@Autowired
	private MatrixValidator validator;


	@RequestMapping(value="/echo", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<InputStreamResource>  echo(@RequestParam(required = true) MultipartFile csv) {
		logger.debug("echo file !");

		if (csv.isEmpty()) {
			return new ResponseEntity("File is empty", HttpStatus.BAD_REQUEST);
		}

		Matrix echoMatrix = null;

		try (BufferedReader br = new BufferedReader(new InputStreamReader(csv.getInputStream()))) {
			echoMatrix = transformer.csvToMatrixTransform(br);
			Validate.isTrue(validator.isValid(echoMatrix));
		}
		catch(IllegalArgumentException e){
			return new ResponseEntity("the Matrix is not valid",HttpStatus.BAD_REQUEST);
		}
		catch(IOException e){
			//it should be it should be customizedd exceptions for every kind of error.
			return new ResponseEntity("Parser failed",HttpStatus.BAD_REQUEST);
		}

		//validate matrix squeare

		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(org.springframework.http.MediaType.TEXT_PLAIN);
		return new ResponseEntity(echoMatrix.toString(), httpHeaders, HttpStatus.OK);

	}



	@RequestMapping(value="/sum", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<InputStreamResource>  sum(@RequestParam(required = true) MultipartFile csv) {

		if (csv.isEmpty()) {
			return new ResponseEntity("File is empty", HttpStatus.BAD_REQUEST);
		}

		Matrix sumMatrix = null;

		try (BufferedReader br = new BufferedReader(new InputStreamReader(csv.getInputStream()))) {
			sumMatrix = transformer.csvToMatrixTransform(br);
			Validate.isTrue(validator.isValid(sumMatrix));
		}
		catch(IllegalArgumentException e){
			return new ResponseEntity("the Matrix is not valid",HttpStatus.BAD_REQUEST);
		}
		catch(IOException e){
			//it should be it should be customizedd exceptions for every kind of error.
			return new ResponseEntity("Parser failed",HttpStatus.BAD_REQUEST);
		}

		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(org.springframework.http.MediaType.TEXT_PLAIN);
		return new ResponseEntity(sumMatrix.sum(), httpHeaders, HttpStatus.OK);

	}




	@RequestMapping(value="/multiply", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<InputStreamResource>  multiply(@RequestParam(required = true) MultipartFile csv) {

		if (csv.isEmpty()) {
			return new ResponseEntity("File is empty", HttpStatus.BAD_REQUEST);
		}

		Matrix multiplyMatrix = null;

		try (BufferedReader br = new BufferedReader(new InputStreamReader(csv.getInputStream()))) {
			multiplyMatrix = transformer.csvToMatrixTransform(br);
			Validate.isTrue(validator.isValid(multiplyMatrix));
		}
		catch(IllegalArgumentException e){
			return new ResponseEntity("the Matrix is not valid",HttpStatus.BAD_REQUEST);
		}
		catch(IOException e){
			//it should be it should be customizedd exceptions for every kind of error.
			return new ResponseEntity("Parser failed",HttpStatus.BAD_REQUEST);
		}

		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(org.springframework.http.MediaType.TEXT_PLAIN);
		return new ResponseEntity(multiplyMatrix.multiply(), httpHeaders, HttpStatus.OK);
	}




	@RequestMapping(value="/flatten", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<InputStreamResource>  flatten(@RequestParam(required = true) MultipartFile csv) {

		if (csv.isEmpty()) {
			return new ResponseEntity("File is empty", HttpStatus.BAD_REQUEST);
		}

		Matrix flattenMatrix = null;

		try (BufferedReader br = new BufferedReader(new InputStreamReader(csv.getInputStream()))) {
			flattenMatrix = transformer.csvToMatrixTransform(br);
			Validate.isTrue(validator.isValid(flattenMatrix));
		}
		catch(IllegalArgumentException e){
			return new ResponseEntity("the Matrix is not valid",HttpStatus.BAD_REQUEST);
		}
		catch(IOException e){
			//it should be it should be customizedd exceptions for every kind of error.
			return new ResponseEntity("Parser failed",HttpStatus.BAD_REQUEST);
		}


		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(org.springframework.http.MediaType.TEXT_PLAIN);
		return new ResponseEntity(flattenMatrix.flatten(), httpHeaders, HttpStatus.OK);

	}





	@RequestMapping(value="/inverse", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<InputStreamResource>  inverse(@RequestParam(required = true) MultipartFile csv) {

		if (csv.isEmpty()) {
			return new ResponseEntity("File is empty", HttpStatus.BAD_REQUEST);
		}

		Matrix inverseMatrix = null;

		try (BufferedReader br = new BufferedReader(new InputStreamReader(csv.getInputStream()))) {
			inverseMatrix = transformer.csvToMatrixTransform(br);
			Validate.isTrue(validator.isValid(inverseMatrix));

		}
		catch(IllegalArgumentException e){
			return new ResponseEntity("the Matrix is not valid",HttpStatus.BAD_REQUEST);
		}
		catch(IOException e){
			//it should be it should be customizedd exceptions for every kind of error.
			return new ResponseEntity("Parser failed",HttpStatus.BAD_REQUEST);
		}

		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(org.springframework.http.MediaType.TEXT_PLAIN);
		return new ResponseEntity(inverseMatrix.transpose().toString(), httpHeaders, HttpStatus.OK);

	}


}
