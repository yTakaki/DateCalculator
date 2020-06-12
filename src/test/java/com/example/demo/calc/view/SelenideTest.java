package com.example.demo.calc.view;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Configuration.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SelenideTest {

	@BeforeEach
	void setup() {
		timeout = 10000;
	}

	@Test
	void testGoogle() throws Exception {
		open("http://www.google.com");
		$("input[type=text]").val("selenide").pressEnter();
		$(byText("Selenide: concise UI tests in Java")).click();
		$("body").shouldHave(text("What is Selenide?"));
	}

	@Test
	void test1() throws Exception {
		open("/home");
	}
}
