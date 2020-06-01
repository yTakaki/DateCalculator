package com.example.demo.calc.controller;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.example.demo.calc.domain.service.FormulaService;

@WebMvcTest(controllers = RegistFormulaController.class)
public class RegistFormulaControllerTest {

	@Autowired
	private MockMvc mock;

	@MockBean
	private FormulaService service;

	@Test
	void 登録ページへのリクエストに対して正常な画面が返されること() throws Exception {
		mock.perform(get("/regist"))
		.andExpect(status().isOk())
		.andExpect(view().name("calc/registFormula"));
	}

	@Test
	void 登録フォームに適正なデータを入力して登録ボタンを押すと登録処理がされて計算画面が返されること() throws Exception {
		mock.perform(post("/regist").param("formulaId","99999").param("formulaName", "testformula")
				.param("designerDay", "29"))
		.andExpect(status().isOk())
		.andExpect(view().name("calc/homeCalculator"));

		verify(service,times(1)).insert(any());
	}


	@Test
	void 登録フォームの日付IDにNullを入力して登録処理をすると例外処理が表示された画面が返されること() throws Exception {
		mock.perform(post("/regist").param("formulaName", "testformula").param("designerDay", "29"))
		.andExpect(status().isOk())
		.andExpect(model().hasErrors())
		.andExpect(view().name("calc/registFormula"));
	}

	@Test
	void 登録フォームの日付IDに空文字を入力して登録処理をすると例外処理が表示された画面が返されること() throws Exception {
		mock.perform(post("/regist").param("formulaId", "")
				.param("formulaName", "testformula").param("designerDay", "29"))
		.andExpect(status().isOk())
		.andExpect(model().hasErrors())
		.andExpect(view().name("calc/registFormula"));
	}

	@Test
	void 登録フォームの日付IDに空白を入力して登録処理をすると例外処理が表示された画面が返されること() throws Exception {
		mock.perform(post("/regist").param("formulaId", " ")
				.param("formulaName", "testformula").param("designerDay", "29"))
		.andExpect(status().isOk())
		.andExpect(model().hasErrors())
		.andExpect(view().name("calc/registFormula"));
	}

	@Test
	void 登録フォームの計算式名にNullを入力して登録処理をすると例外処理が表示された画面が返されること() throws Exception {
		mock.perform(post("/regist").param("formulaId", "99999")
				.param("designerDay", "29"))
		.andExpect(status().isOk())
		.andExpect(model().hasErrors())
		.andExpect(view().name("calc/registFormula"));
	}

	@Test
	void 登録フォームの計算式名に空文字を入力して登録処理をすると例外処理が表示された画面が返されること() throws Exception {
		mock.perform(post("/regist").param("formulaId", "99999")
				.param("formulaName", "").param("designerDay", "29"))
		.andExpect(status().isOk())
		.andExpect(model().hasErrors())
		.andExpect(view().name("calc/registFormula"));
	}

	@Test
	void 登録フォームの計算式名に空白を入力して登録処理をすると例外処理が表示された画面が返されること() throws Exception {
		mock.perform(post("/regist").param("formulaId", "99999")
				.param("formulaName", " ").param("designerDay", "29"))
		.andExpect(status().isOk())
		.andExpect(model().hasErrors())
		.andExpect(view().name("calc/registFormula"));
	}

	@Test
	void 登録フォームの指定日に負の値を入力して登録処理をすると例外処理が表示された画面が返されること() throws Exception {
		mock.perform(post("/regist").param("formulaId", "99999")
				.param("formulaName", "testformula").param("designerDay", "-1"))
		.andExpect(status().isOk())
		.andExpect(model().hasErrors())
		.andExpect(view().name("calc/registFormula"));
	}

	@Test
	void 登録フォームの指定日に30を入力して登録処理をすると例外処理が表示された画面が返されること() throws Exception {
		mock.perform(post("/regist").param("formulaId", "99999")
				.param("formulaName", "testformula").param("designerDay", "30"))
		.andExpect(status().isOk())
		.andExpect(model().hasErrors())
		.andExpect(view().name("calc/registFormula"));
	}
}
