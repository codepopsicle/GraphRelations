package com.exb.springboot;

import com.exb.springboot.exception.InvalidFormatException;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import org.apache.commons.lang3.tuple.*;

@RestController
public class ApiController {


	private Task1 task1;
	private Task2 task2;

	@Autowired
	public ApiController(Task1 task1, Task2 task2){
		this.task1 = task1;
		this.task2 = task2;
	}

	@ApiOperation(value = "API greeting", notes = "")
	@ApiResponses(value = {
			@ApiResponse(code = 500, message = "Server error"),
			@ApiResponse(code = 404, message = "Service not found"),
			@ApiResponse(code = 200, message = "Successful retrieval",
					response = String.class) })
	@RequestMapping("/")
	public String index() {
		return "Welcome to relation graph API!";
	}

	@ApiOperation(value = "API for task1", notes = "Returns a pair with two lists: nodes with " +
			"zero parents, and nodes with one parent", nickname = "task1")
	@ApiResponses(value = {
			@ApiResponse(code = 500, message = "Server error"),
			@ApiResponse(code = 404, message = "Service not found"),
			@ApiResponse(code = 200, message = "Successful retrieval",
					response = Pair.class) })
	@RequestMapping(path = "/task1", method = RequestMethod.GET)
	public Pair task1(@RequestBody String inputJSON) throws InvalidFormatException{
		return task1.baseAlgorithm(ControllerUtils.getInputGraph(inputJSON));
	}


	@ApiOperation(value = "API for task2", notes = "Returns a boolean(true), if the complex relationship is" +
			"satisfied for the given set of input nodes", nickname = "task2")
	@ApiResponses(value = {
			@ApiResponse(code = 500, message = "Server error"),
			@ApiResponse(code = 404, message = "Service not found"),
			@ApiResponse(code = 200, message = "Successful retrieval",
					response = Boolean.class) })
	@RequestMapping(path = "/task2", method = RequestMethod.GET)
	public boolean task2(@RequestBody String inputJSON) throws InvalidFormatException{

		int[][] nodesArr = new int[1][2];

		nodesArr[0][0] = Integer.parseInt(new JSONObject(inputJSON).getJSONObject("nodes").get("node1").toString());
		nodesArr[0][1] = Integer.parseInt(new JSONObject(inputJSON).getJSONObject("nodes").get("node2").toString());

		return task2.shareCommonAncestor(ControllerUtils.getInputGraph(inputJSON), nodesArr);
	}

}
