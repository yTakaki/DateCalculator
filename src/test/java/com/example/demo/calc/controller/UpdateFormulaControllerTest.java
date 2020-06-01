package com.example.demo.calc.controller;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import com.example.demo.calc.domain.model.Formula;
import com.example.demo.calc.domain.service.FormulaService;

@SpringBootTest
@AutoConfigureMockMvc
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

	/* // 初期に書いていたテスト（自戒のため残しておく）
	@BeforeEach
	void beforeTest() throws Exception {
		Formula f = new Formula();
		f.setFormulaId("99999");
		f.setFormulaName("testdata");
		f.setValueDay(1);
		service.delete("99999");
		service.insert(f);
	}

	@Test
	void modelとviewのTest() throws Exception {
		this.mock.perform(get("/update/99999"))
		.andExpect(status().isOk())
		.andExpect(model().attribute("registFormulaForm", hasProperty("formulaId",is("99999"))))
		.andExpect(model().attribute("registFormulaForm", hasProperty("formulaName",is("testdata"))))
		.andExpect(model().attribute("registFormulaForm", hasProperty("valueYear",is(0))))
		.andExpect(model().attribute("registFormulaForm", hasProperty("valueMonth",is(0))))
		.andExpect(model().attribute("registFormulaForm", hasProperty("valueDay",is(1))))
		.andExpect(model().attribute("registFormulaForm", hasProperty("designerDay",is(0))))
		.andExpect(view().name("calc/updateFormula"));
	}

	@Test
	void postUpdateTest() throws Exception {
		this.mock.perform(post("/update").param("formulaId","99999")
				.param("formulaName","testdata").param("valueDay","2"))
		.andExpect(model().attribute("result",is("計算式(99999)を1件、更新しました。")));
	}
	*/
}
