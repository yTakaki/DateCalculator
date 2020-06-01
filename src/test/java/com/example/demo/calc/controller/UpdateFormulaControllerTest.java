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

import com.example.demo.calc.domain.model.Formula;
import com.example.demo.calc.domain.service.FormulaService;

@WebMvcTest(controllers = UpdateFormulaController.class)
@TestPropertySource(locations = "classpath:test.properties")
public class UpdateFormulaControllerTest {

	@Autowired
	private MockMvc mock;

	@MockBean
	private FormulaService service;

	@Test
	void 更新ページへのリクエストに対して正常な画面を返すこと() throws Exception {
		when(service.selectOne(anyString())).thenReturn(new Formula("00001","testdata",0,0,1,0)); // to avoid NullPointerException for junit test.
		mock.perform(get("/update/{formulaId}","00001"))
		.andExpect(status().isOk())
		.andExpect(view().name("calc/updateFormula"));

		verify(service,times(1)).selectOne("00001");
	}

	@Test
	void 更新ページで更新ボタンを押すと更新処理されて計算画面を返すこと() throws Exception {
		mock.perform(post("/update").param("formulaId", "00001").param("formulaName", "testdata2")
				.param("valueYear","0").param("valueMonth","0").param("valueDay","1").param("designerDay","0"))
		.andExpect(status().isOk())
		.andExpect(view().name("calc/homeCalculator"));

		verify(service,times(1)).update(any());
	}

	@Test
	void 更新ページで計算式名にNullで更新処理をすると例外情報が画面に返されること() throws Exception {
		when(service.selectOne(anyString())).thenReturn(new Formula("00001","testdata",0,0,1,0)); // to avoid NullPointerException for junit test.
		mock.perform(post("/update").param("formulaId", "00001")
				.param("valueYear","0").param("valueMonth","0").param("valueDay","1").param("designerDay","0"))
		.andExpect(status().isOk())
		.andExpect(model().hasErrors())
		.andExpect(view().name("calc/updateFormula"));
	}

	@Test
	void 更新ページで計算式名に空文字で更新処理をすると例外情報が画面に返されること() throws Exception {
		when(service.selectOne(anyString())).thenReturn(new Formula("00001","testdata",0,0,1,0)); // to avoid NullPointerException for junit test.
		mock.perform(post("/update").param("formulaId", "00001").param("formulaName","")
				.param("valueYear","0").param("valueMonth","0").param("valueDay","1").param("designerDay","0"))
		.andExpect(status().isOk())
		.andExpect(model().hasErrors())
		.andExpect(view().name("calc/updateFormula"));
	}

	@Test
	void 更新ページで計算式名に空白で更新処理をすると例外情報が画面に返されること() throws Exception {
		when(service.selectOne(anyString())).thenReturn(new Formula("00001","testdata",0,0,1,0)); // to avoid NullPointerException for junit test.
		mock.perform(post("/update").param("formulaId", "00001").param("formulaName"," ")
				.param("valueYear","0").param("valueMonth","0").param("valueDay","1").param("designerDay","0"))
		.andExpect(status().isOk())
		.andExpect(model().hasErrors())
		.andExpect(view().name("calc/updateFormula"));
	}

	@Test
	void 更新ページで計算式名が51文字で更新処理をすると例外情報が画面に返されること() throws Exception {
		when(service.selectOne(anyString())).thenReturn(new Formula("00001","testdata",0,0,1,0)); // to avoid NullPointerException for junit test.
		mock.perform(post("/update").param("formulaId", "00001")
				.param("formulaName","123456789012345678901234567890123456789012345678901")
				.param("valueYear","0").param("valueMonth","0").param("valueDay","1").param("designerDay","0"))
		.andExpect(status().isOk())
		.andExpect(model().hasErrors())
		.andExpect(view().name("calc/updateFormula"));
	}

	@Test
	void 更新ページで指定日を負の値で更新処理をすると例外情報が画面に返されること() throws Exception {
		when(service.selectOne(anyString())).thenReturn(new Formula("00001","testdata",0,0,1,0)); // to avoid NullPointerException for junit test.
		mock.perform(post("/update").param("formulaId", "00001").param("formulaName","testdata")
				.param("valueYear","0").param("valueMonth","0").param("valueDay","1").param("designerDay","-1"))
		.andExpect(status().isOk())
		.andExpect(model().hasErrors())
		.andExpect(view().name("calc/updateFormula"));
	}

	@Test
	void 更新ページで指定日を30で更新処理をすると例外情報が画面に返されること() throws Exception {
		when(service.selectOne(anyString())).thenReturn(new Formula("00001","testdata",0,0,1,0)); // to avoid NullPointerException for junit test.
		mock.perform(post("/update").param("formulaId", "00001").param("formulaName","testdata")
				.param("valueYear","0").param("valueMonth","0").param("valueDay","1").param("designerDay","30"))
		.andExpect(status().isOk())
		.andExpect(model().hasErrors())
		.andExpect(view().name("calc/updateFormula"));
	}

}
