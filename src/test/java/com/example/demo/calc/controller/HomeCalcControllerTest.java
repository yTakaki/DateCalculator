package com.example.demo.calc.controller;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.calc.domain.model.CalcResult;
import com.example.demo.calc.domain.service.CalcService;

@AutoConfigureMockMvc
@SpringBootTest
@Transactional
public class HomeCalcControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private CalcService service;

	@Test
	void homeへのGETリクエストに対するhomeCalculatorのビュー表示確認のテスト() throws Exception {
		this.mockMvc.perform(get("/home"))
		.andExpect(status().isOk())
		.andExpect(view().name("calc/homeCalculator"));
	}

	@Test
	void 計算実行に対する計算結果がmodelに反映されるかテスト() throws Exception {
		{
			CalcResult f = new CalcResult();
			f.setFormulaId("99999");
			f.setFormulaName("testdata");
			f.setResultDate(LocalDate.of(2020,5,6));
			f.setValueSet("0/0/1");
			f.setDesignerDay(0);
			List<CalcResult> list = new ArrayList<>();
			list.add(f);

			when(service.calculation(LocalDate.of(2020,5,5))).thenReturn(list);
		}
		this.mockMvc.perform(post("/home").param("calcDate","2020/05/05"))
		.andExpect(model().attribute("resultList",hasItems(hasProperty("resultDate",is(LocalDate.of(2020,5,6))))));
	}

}
