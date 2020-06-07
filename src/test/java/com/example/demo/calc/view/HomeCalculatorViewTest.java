package com.example.demo.calc.view;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Configuration.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import com.example.demo.calc.view.page.HomeCalculatorPage;

@SpringBootTest
@TestPropertySource(locations="classpath:test.properties")
public class HomeCalculatorViewTest {

	private HomeCalculatorPage page;

	@BeforeAll
	public static void setUp() {
		holdBrowserOpen = false;
	}

	@BeforeEach
	public void setUpTest() {
		page = HomeCalculatorPage.open();
	}

	@Test
	public void 計算画面で計算基準日に2020年5月5日の日付を入れて結果が一覧で取得できる事() {
		HomeCalculatorPage actual = page.calcDate("2020-05-05").calculation();

		actual.calcResult().shouldBe(visible);
		assertThat(actual.countCalcResult(),is(2));
	}

	/*
	@Test
	public void 新規登録画面へ遷移できる事() throws Exception {
		RegistFormulaPage actual = page.moveToRegistFormulaPage();

		assertThat(actual.title(),is("Regist Formula"));
	}

	@Test
	public void 計算画面から更新画面へ遷移できる事() throws Exception {
		page.calcDate("2020-05-05").calculation();

		UpdateFormulaPage actual = page.moveToUpdateFormulaPage("00001");

		assertThat(actual.title(),is("Regist Formula"));
	}

	@Test
	public void 計算画面から削除実行出来る事() throws Exception {
		page.calcDate("2020-05-05").calculation();

		page.clickDeleteButton("00001");
		HomeCalculatorPage actual = page.calcDate("2020-05-05").calculation();

		actual.calcResult().shouldBe(visible);
		assertThat(actual.countCalcResult(),is(1));
	}
	*/
}
