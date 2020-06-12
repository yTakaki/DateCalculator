package com.example.demo.calc.view;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
public class SelenideTest {

	@Test
	void testGoogle() throws Exception {
		open("http://www.google.com");
		$("input[type=text]").val("selenide").pressEnter();
		$(byText("Selenide: concise UI tests in Java")).click();
		$("body").shouldHave(text("What is Selenide?"));
	}

	@Test
	void test1_計算画面に計算基準日の文字が表示されること() throws Exception {
		open("/home");
		$("body").shouldHave(text("計算基準日"));
	}
}
