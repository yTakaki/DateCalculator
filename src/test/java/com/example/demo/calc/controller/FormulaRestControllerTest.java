package com.example.demo.calc.controller;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.calc.domain.model.Formula;
import com.example.demo.calc.domain.service.FormulaService;
import com.fasterxml.jackson.databind.ObjectMapper;

@AutoConfigureMockMvc
@SpringBootTest
@Transactional
public class FormulaRestControllerTest {

	@Autowired
	private MockMvc mock;

	@Autowired
	private FormulaService formulaService;

	@BeforeEach
	void beforeTest() throws Exception {
		Formula f = new Formula();
		f.setFormulaId("99999");
		f.setFormulaName("testdata");
		f.setValueDay(1);
		formulaService.delete("99999");
		formulaService.insert(f);
	}

	@Test
	void getSelectOneTest() throws Exception {
		String responseJsonString = this.mock.perform(get("/formulaList/99999"))
		.andExpect(status().isOk())
		.andReturn().getResponse().getContentAsString();
		ObjectMapper mapper = new ObjectMapper();
		Formula responseJson = mapper.readValue(responseJsonString,Formula.class);
		Formula expect = new Formula();
		expect.setFormulaId("99999");
		expect.setFormulaName("testdata");
		expect.setValueDay(1);
		assertThat(responseJson,is(expect));
	}

}
