package com.example.demo.calc.controller;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.calc.domain.service.FormulaService;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class RegistFormulaControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private FormulaService service;

	@BeforeEach
	void beforeTest() throws Exception {
		service.delete("99999");
	}

	@Test
	void registへのGETリクエストに対するregistFormulaビュー表示確認のテスト() throws Exception {
		this.mockMvc.perform(get("/regist"))
		.andExpect(status().isOk())
		.andExpect(view().name("calc/registFormula"));
	}

	@Test
	void registへのPOSTリクエストに対するテスト() throws Exception {
		// execute and assertion
		this.mockMvc.perform(post("/regist")
				.param("formulaId","99999")
				.param("formulaName","testdata")
				.param("valueDay","1"))
		.andExpect(model().hasNoErrors())
		.andExpect(model().attribute("result",is("計算式を1件、追加しました。")))
		.andExpect(view().name("calc/homeCalculator"));
		//
	}
}
