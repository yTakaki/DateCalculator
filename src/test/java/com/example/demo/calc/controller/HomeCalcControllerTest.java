package com.example.demo.calc.controller;

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
	void homeへのGET処理でレスポンスが返ってくるテスト() throws Exception {
		this.mockMvc.perform(get("/home")).andExpect(status().isOk());
	}

}
