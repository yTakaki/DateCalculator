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

import com.example.demo.calc.domain.model.Formula;
import com.example.demo.calc.domain.service.FormulaService;

@AutoConfigureMockMvc
@SpringBootTest
@Transactional
public class UpdateFormulaControllerTest {

	@Autowired
	private MockMvc mock;

	@Autowired
	private FormulaService service;

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
}
