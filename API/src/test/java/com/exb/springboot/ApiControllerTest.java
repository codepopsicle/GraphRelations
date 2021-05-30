package com.exb.springboot;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.io.File;
import java.nio.file.Files;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ApiControllerTest {

	@Autowired
	private MockMvc mvc;

	@Autowired
	private ResourceLoader resourceLoader;

	@Test
	public void testWelcomeMessage() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().string(equalTo("Welcome to relation graph API!")));
	}

	@Test
	public void task1Test() throws Exception {

		/* Read input from file */
		final File inputFile = resourceLoader.getResource("classpath:task1-input.json").getFile();
		final File outputFile = resourceLoader.getResource("classpath:task1-output.json").getFile();

		final String inputString = new String(Files.readAllBytes(inputFile.toPath()));
		final String outputString = new String(Files.readAllBytes(outputFile.toPath()));

		final JSONObject inputJson = new JSONObject(inputString);
		final JSONObject outputJson = new JSONObject(outputString);

		mvc.perform(MockMvcRequestBuilders.get("/task1").contentType(MediaType.APPLICATION_JSON)
			.content(inputJson.toString()))
				.andExpect(status().isOk())
				.andExpect(content().string(equalTo(outputJson.toString())));
	}

	@Test
	public void task2Test() throws Exception {

		/* Read input from file */
		final File trueInputFile = resourceLoader.getResource("classpath:task2-input-true.json").getFile();
		final File falseInputFile = resourceLoader.getResource("classpath:task2-input-false.json").getFile();

		final String trueInputString = new String(Files.readAllBytes(trueInputFile.toPath()));
		final String falseInputString = new String(Files.readAllBytes(falseInputFile.toPath()));

		final JSONObject inputTrueJson = new JSONObject(trueInputString);
		final JSONObject inputFalseJson = new JSONObject(falseInputString);

		/* Check a true case */
		mvc.perform(MockMvcRequestBuilders.get("/task2").contentType(MediaType.APPLICATION_JSON)
				.content(inputTrueJson.toString()))
				.andExpect(status().isOk())
				.andExpect(content().string("true"));

		/* checking false case */
		mvc.perform(MockMvcRequestBuilders.get("/task2").contentType(MediaType.APPLICATION_JSON)
				.content(inputFalseJson.toString()))
				.andExpect(status().isOk())
				.andExpect(content().string("false"));
	}

}
