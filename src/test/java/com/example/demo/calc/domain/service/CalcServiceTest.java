package com.example.demo.calc.domain.service;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import com.example.demo.calc.domain.model.Formula;
import com.example.demo.calc.domain.repository.FormulaMapper;

@SpringJUnitConfig(classes = CalcServiceTest.Config.class)
public class CalcServiceTest {

	@ComponentScan({
		"com.example.demo.calc.domain.service"
	})
	static class Config {
	}

	@Autowired
	private CalcService sut;

	@MockBean
	private FormulaMapper mapper;

	@Nested
	class 例外処理 {
		@Test
		void 計算基準日がNullのときNullPointerExceptionを返すこと() throws Exception {
			Formula f = new Formula("99999","test",0,0,0,1);
			when(mapper.selectAll()).thenReturn(List.of(f));
			assertThrows(NullPointerException.class,() -> sut.calculation(null));
		}

		@Test
		void 計算基準日が不適切な日付のときDateTimeExceptionを返すこと() throws Exception {
			assertThrows(DateTimeException.class,() -> sut.calculation(LocalDate.of(2020, 9, 31)));
		}
	}

	@Nested
	class 日付計算 {
		@ParameterizedTest
		@CsvSource(value = {
				"2020-05-05, 0, 0, 0, 0, 2020-05-05", // すべて未指定(指定日なし）
				"2020-05-05, 0, 0, 0, 20, 2020-05-20", // 日付指定(日付<指定日)
				"2020-05-25, 0, 0, 0, 20, 2020-05-20", // 日付指定(日付>指定日)
				"2020-05-05, 0, 0, 0, 29, 2020-05-31", // 末日指定(加算日なし)
				"2020-05-05, 0, 0, -10, 29, 2020-05-31", // 末日指定(加算日あり)
				"2020-05-05, 0, 0, 1, 0, 2020-05-06", // 翌日
				"2020-05-05, 0, 1, 0, 0, 2020-06-05", // 翌月
				"2020-05-05, 1, 0, 0, 0, 2021-05-05", // 翌年
				"2020-05-05, 0, 0, -1, 0, 2020-05-04", // 前日
				"2020-05-05, 0, -1, 0, 0, 2020-04-05", // 前月
				"2020-05-05, -1, 0, 0, 0, 2019-05-05", // 前年
				"2020-05-05, 1, 1, 1, 1, 2021-06-01", // すべて指定(指定日あり)
				"2020-05-31, 0, 1, 0, 0, 2020-06-30", // 月の加算で日数の切り捨て
				"2020-05-05, 0, 12, 0, 0, 2021-05-05", // 年月またぎ
				"2020-05-05, 0, 0, 365, 0, 2021-05-05", // 年月またぎ
		})
		void test(LocalDate calcDate,int valueYear,int valueMonth,int valueDay,
				int designerDay,LocalDate expect) throws Exception {
			Formula f = new Formula("99999","test",valueYear,valueMonth,valueDay,designerDay);
			when(mapper.selectAll()).thenReturn(List.of(f));
			LocalDate actual = sut.calculation(calcDate).get(0).getResultDate();
			assertThat(actual,is(expect));
		}
	}



}