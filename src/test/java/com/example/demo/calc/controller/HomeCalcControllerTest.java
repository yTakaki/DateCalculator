package com.example.demo.calc.controller;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@AutoConfigureMockMvc
@SpringBootTest
public class HomeCalcControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Test
	void homeへのGETリクエストに対するhomeCalculatorのビュー表示確認のテスト() throws Exception {
		this.mockMvc.perform(get("/home")).andExpect(status().isOk()).andExpect(view().name("calc/homeCalculator"));
	}

	@Test
	void 計算実行に対する計算結果にformulaIdが反映されるかテスト() throws Exception {
		this.mockMvc.perform(post("/home").param("calcDate","2020/05/05"))
		.andExpect(model().attribute("resultList",hasItem(hasProperty("formulaId"))));
	}
}
