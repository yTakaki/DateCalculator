package com.example.demo.calc.domain.service;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import com.example.demo.calc.domain.model.CalcResult;
import com.example.demo.calc.domain.model.Formula;
import com.example.demo.calc.domain.repository.FormulaMapper;

@SpringJUnitConfig(classes = CalcServiceTest.Config.class)
public class CalcServiceTest {

	@ComponentScan({
		"com.example.demo.calc.domain.service",
		"com.example.demo.calc.domain.repository"
	})
	static class Config {
	}

	public static class 例外処理 {

		@Autowired
		private CalcService sut;

		@Test
		void 計算基準日がNullのときNullPointerExceptionを返すこと() throws Exception {
			assertThrows(NullPointerException.class,() -> sut.calculation(null));
		}

		@Test
		void 計算基準日が不適切な日付のときDateTimeExceptionを返すこと() throws Exception {
			assertThrows(DateTimeException.class,() -> sut.calculation(LocalDate.of(2020, 9, 31)));
		}

	}

	public static class 日付計算 {

		@Autowired
		private CalcService sut;

		private FormulaMapper mapper = mock(FormulaMapper.class);

		@ParameterizedTest
		@CsvSource(value = {
				"2018-12-01, 0, 0, 0, 0, 2018-12-01", // すべて未指定(指定日なし）
				"2018-12-01, 0, 0, 0, 20, 2018-12-20", // 日付指定(日付<指定日)
		})
		void test(LocalDate calcDate,int valueYear,int valueMonth,int valueDay,
				int designerDay,LocalDate expect) throws Exception {
			Formula f = new Formula("99999","test",valueYear,valueMonth,valueDay,designerDay);
			when(mapper.selectAll()).thenReturn(List.of(f));
			List<CalcResult> result = sut.calculation(calcDate);
			LocalDate actual = result.get(0).getResultDate();
			assertThat(actual,is(expect));
		}
	}
}

/*

				"2018-12-31, 0, 0, 0, 20, 2018-12-20", // 日付指定(日付>指定日)
				"2018-12-01, 0, 0, 0, 29, 2018-12-31", // 末日指定(加算日なし)
				"2018-12-01, 0, 0, -1, 29, 2018-12-31", // 末日指定(加算日あり)
				"2018-12-01, 0, 0, 1, 0, 2018-12-02", // 翌日
				"2018-12-01, 0, 0, -1, 0, 2018-11-30", // 前日
				"2018-11-01, 0, 1, 0, 0, 2018-12-01", // 翌月
				"2018-12-01, 0, -1, 0, 0, 2018-11-01", // 前月
				"2018-12-01, 1, 0, 0, 0, 2019-12-01", // 翌年
				"2018-12-01, -1, 0, 0, 0, 2017-12-01", // 前年
				"2018-12-01, 1, 1, 1, 1, 2020-01-01", // すべて指定(指定日あり)
				"2018-10-31, 0, 1, 0, 0, 2018-11-30", // 月の加算で日数の切り捨て
				"2018-12-02, 0, 13, 0, 0, 2020-01-02", // 年月またぎ
				"2018-12-02, 0, 0, 365, 0, 2019-12-02", // 年月またぎ
 */
