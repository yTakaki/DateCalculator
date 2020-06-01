package com.example.demo.calc.controller;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import com.example.demo.calc.domain.service.CalcService;
import com.example.demo.calc.domain.service.FormulaService;

@WebMvcTest(HomeCalcController.class)
@TestPropertySource(locations = "classpath:test.properties")
public class HomeCalcControllerTest {

	@Autowired
	private MockMvc mock;

	@MockBean
	private CalcService service;

	@MockBean
	private FormulaService service2;

	@Test
	void 計算用ページへのリクエストに対して正常にビューが表示されること() throws Exception {
		mock.perform(get("/home"))
		.andExpect(status().isOk())
		.andExpect(view().name("calc/homeCalculator"));
	}

	@Test
	void 計算用ページにて計算基準日を入力して計算実行を押すとCalcServiceが実行されること() throws Exception {
		mock.perform(post("/home").param("calcDate","2020/05/05"))
		.andExpect(status().isOk())
		.andExpect(view().name("calc/homeCalculator"));

		verify(service,times(1)).calculation(any());
	}

	@Test
	void 計算用ページにて削除ボタンを押すと削除処理されて同一画面に戻ること() throws Exception {
		mock.perform(post("/delete/{formulaId}","00001"))
		.andExpect(status().isOk())
		.andExpect(view().name("calc/homeCalculator"));

		verify(service2,times(1)).delete("00001");
	}

	@Test
	void 計算用ページにて計算基準日がNULLで計算実行されたとき例外情報が画面に返されること() throws Exception {
		mock.perform(post("/home"))
		.andExpect(model().hasErrors())
		.andExpect(view().name("calc/homeCalculator"));
	}

	@Test
	void 計算用ページにて計算基準日が不適正な値で計算実行されたとき例外情報が画面に返されること() throws Exception {
		mock.perform(post("/home").param("calcDate", "2020/5/5"))
		.andExpect(model().hasErrors())
		.andExpect(view().name("calc/homeCalculator"));
	}
}
