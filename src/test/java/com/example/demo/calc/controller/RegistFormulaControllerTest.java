package com.example.demo.calc.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class RegistFormulaControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Test
	void registへのGETリクエストに対するregistFormulaビュー表示確認のテスト() throws Exception {
		this.mockMvc.perform(get("/regist"))
		.andExpect(status().isOk())
		.andExpect(view().name("calc/registFormula"));
	}

	@Disabled
	@Test
	void registへのPOSTリクエストに対するテスト() throws Exception {
		this.mockMvc.perform(post("/regist"));
	}
}
